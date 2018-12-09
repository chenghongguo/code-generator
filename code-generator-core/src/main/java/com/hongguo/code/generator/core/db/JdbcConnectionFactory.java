package com.hongguo.code.generator.core.db;

import com.hongguo.code.generator.config.ContextConfiguration;
import com.hongguo.code.generator.config.JdbcConnectionConfiguration;
import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
@Data
public class JdbcConnectionFactory implements ConnectionFactory {

    private ContextConfiguration context;

    @Override
    public Connection getConnection() {
        JdbcConnectionConfiguration config = context.getJdbcConnectionConfiguration();
        Connection connection = null;
        try {
            Class.forName(config.getDriverClass());
            connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // ignore
            }
        }
    }
}
