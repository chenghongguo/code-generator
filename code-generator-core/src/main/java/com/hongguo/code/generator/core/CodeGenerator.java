package com.hongguo.code.generator.core;

import com.hongguo.code.generator.common.db.DatabaseIntrospector;
import com.hongguo.code.generator.config.CodeGeneratorConfiguration;
import com.hongguo.code.generator.config.TableConfiguration;
import com.hongguo.code.generator.core.db.JdbcConnectionFactory;
import lombok.Data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
@Data
public class CodeGenerator {

    private CodeGeneratorConfiguration configuration;

    public CodeGenerator(CodeGeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    public void generator() {
        JdbcConnectionFactory factory = new JdbcConnectionFactory();
        factory.setContext(configuration.getContextConfiguration());
        Connection connection = null;
        try {
            connection = factory.getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            System.out.println(databaseMetaData);

            DatabaseIntrospector introspector = new DatabaseIntrospector(configuration.getContextConfiguration(), databaseMetaData);
            List<TableConfiguration> tableConfigurations = configuration.getContextConfiguration().getTableConfigurations();
            for (TableConfiguration tableConfiguration : tableConfigurations) {
                introspector.introspectTables(tableConfiguration);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            factory.closeConnection(connection);
        }
    }
}
