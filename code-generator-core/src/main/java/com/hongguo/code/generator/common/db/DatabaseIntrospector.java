package com.hongguo.code.generator.common.db;

import com.hongguo.code.generator.config.ContextConfiguration;
import com.hongguo.code.generator.config.TableConfiguration;
import lombok.Data;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
@Data
public class DatabaseIntrospector {

    private DatabaseMetaData databaseMetaData;

    private ContextConfiguration context;

    public DatabaseIntrospector(ContextConfiguration context, DatabaseMetaData databaseMetaData) {
        this.databaseMetaData = databaseMetaData;
        this.context = context;
    }

    public List<IntrospectedTable> introspectTables(TableConfiguration tableConfiguration) {
        Map<ActualTableName, List<IntrospectedColumn>> columns = getColumns(tableConfiguration);
        List<IntrospectedTable> introspectedTables = calculateIntrospectedTables(
                tableConfiguration, columns);
        return introspectedTables;
    }

    private List<IntrospectedTable> calculateIntrospectedTables(TableConfiguration tableConfiguration, Map<ActualTableName, List<IntrospectedColumn>> columns) {
        List<IntrospectedTable> tables = new ArrayList<>();
        for (Map.Entry<ActualTableName, List<IntrospectedColumn>> entry : columns.entrySet()) {
            FullyQualifiedTable fullyQualifiedTable = new FullyQualifiedTable(tableConfiguration.getCatalog(), tableConfiguration.getSchema(),
                    tableConfiguration.getTableName(), tableConfiguration.getDomainName(), context);
            IntrospectedTable introspectedTable = new IntrospectedTable();
            introspectedTable.setContext(context);
            introspectedTable.setFullyQualifiedTable(fullyQualifiedTable);
            introspectedTable.setTableConfiguration(tableConfiguration);
            for (IntrospectedColumn column : entry.getValue()) {
                introspectedTable.addColumn(column);
            }

            calculatePrimaryKeyColumn(fullyQualifiedTable, introspectedTable);

            tables.add(introspectedTable);
        }
        return tables;
    }

    private void calculatePrimaryKeyColumn(FullyQualifiedTable fullyQualifiedTable, IntrospectedTable introspectedTable) {
        ResultSet resultSet = null;
        try {
            resultSet = databaseMetaData.getPrimaryKeys(fullyQualifiedTable.getCatalog(), fullyQualifiedTable.getSchema(), fullyQualifiedTable.getTableName());
        } catch (SQLException e) {
            closeResultSet(resultSet);
            return;
        }
        try {
            Map<Short, String> keyColumns = new TreeMap<>();
            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                short keySeq = resultSet.getShort("KEY_SEQ");
                keyColumns.put(keySeq, columnName);
            }
            for (String columnName : keyColumns.values()) {
                introspectedTable.addPrimaryKey(columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
        }
    }

    private Map<ActualTableName, List<IntrospectedColumn>> getColumns(TableConfiguration tableConfiguration) {
        Map<ActualTableName, List<IntrospectedColumn>> answer = new HashMap<>();
        ResultSet resultSet = null;
        try {
            resultSet = databaseMetaData.getColumns(tableConfiguration.getCatalog(), tableConfiguration.getSchema(), tableConfiguration.getTableName(), "%");
            boolean supportsIsAutoIncrement = false;
            boolean supportsIsGeneratedColumn = false;
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int colCount = rsmd.getColumnCount();
            for (int i = 1; i <= colCount; i++) {
                if ("IS_AUTOINCREMENT".equals(rsmd.getColumnName(i))) {
                    supportsIsAutoIncrement = true;
                }
                if ("IS_GENERATEDCOLUMN".equals(rsmd.getColumnName(i))) {
                    supportsIsGeneratedColumn = true;
                }
            }
            while (resultSet.next()) {
                IntrospectedColumn column = new IntrospectedColumn();
                column.setContext(context);
                column.setJdbcType(resultSet.getInt("DATA_TYPE"));
                column.setLength(resultSet.getInt("COLUMN_SIZE"));
                column.setColumnName(resultSet.getString("COLUMN_NAME"));
                column.setDefaultValue(resultSet.getString("COLUMN_DEF"));
                column.setNullable(resultSet.getInt("NULLABLE") == DatabaseMetaData.columnNullable);
                column.setScale(resultSet.getInt("DECIMAL_DIGITS"));

                if (supportsIsAutoIncrement) {
                    column.setAutoIncrement("YES".equalsIgnoreCase(resultSet.getString("IS_AUTOINCREMENT")));
                }

                if (supportsIsGeneratedColumn) {
                    column.setGeneratedColumn(
                            "YES".equals(resultSet.getString("IS_GENERATEDCOLUMN")));
                }

                ActualTableName actualTableName = new ActualTableName(resultSet.getString("TABLE_CAT"),
                        resultSet.getString("TABLE_SCHEM"), resultSet.getString("TABLE_NAME"));

                List<IntrospectedColumn> columnList = answer.get(actualTableName);
                if (columnList == null) {
                    columnList = new ArrayList<>();
                    answer.put(actualTableName, columnList);
                }
                columnList.add(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
        }
        return answer;
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // ignore
            }
        }
    }
}
