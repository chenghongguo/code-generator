package com.hongguo.code.generator.config;


import com.hongguo.code.generator.common.db.DatabaseIntrospector;
import com.hongguo.code.generator.common.db.IntrospectedTable;
import com.hongguo.code.generator.core.db.JdbcConnectionFactory;
import lombok.Data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
@Data
public class ContextConfiguration {

    private JdbcConnectionConfiguration jdbcConnectionConfiguration;

    private JavaModelConfiguration javaModelConfiguration;

    private JavaMapperConfiguration javaMapperConfiguration;

    private SqlMapperConfiguration sqlMapperConfiguration;

    private List<TableConfiguration> tableConfigurations;

    private List<IntrospectedTable> introspectedTables;

    public ContextConfiguration() {
        tableConfigurations = new ArrayList<>();
    }

    public void addTableConfiguration(TableConfiguration tc) {
        tableConfigurations.add(tc);
    }

    public void introspectTables() {
        introspectedTables = new ArrayList<>();
        Connection connection = null;
        JdbcConnectionFactory connectionFactory = new JdbcConnectionFactory();
        try {
            connection = connectionFactory.getConnection();
            DatabaseIntrospector databaseIntrospector = new DatabaseIntrospector(this, connection.getMetaData());

            for (TableConfiguration tableConfiguration : tableConfigurations) {
                List<IntrospectedTable> tables = databaseIntrospector.introspectTables(tableConfiguration);
                introspectedTables.addAll(tables);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connectionFactory.closeConnection(connection);
        }
    }
}
