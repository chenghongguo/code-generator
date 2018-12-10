package com.hongguo.code.generator.common.db;

import com.hongguo.code.generator.config.ContextConfiguration;
import com.hongguo.code.generator.config.TableConfiguration;
import lombok.Data;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<IntrospectedTable> tables = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            Map<String, List<IntrospectedColumn>> columns = getColumns(tableConfiguration);
            resultSet = databaseMetaData.getTables(tableConfiguration.getCatalog(), tableConfiguration.getSchema(), null, null);
            while (resultSet.next()) {
                String catalog = resultSet.getString("TABLE_CAT");
                String tableSchema = resultSet.getString("TABLE_SCHEM");
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println(catalog + ", " + tableSchema + ", " + tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
        }
        return tables;
    }

    private Map<String, List<IntrospectedColumn>> getColumns(TableConfiguration tableConfiguration) {
        return null;
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
