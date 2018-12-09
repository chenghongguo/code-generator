package com.hongguo.code.generator.core.db;

import java.sql.Connection;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
public interface ConnectionFactory {

    Connection getConnection();

    void closeConnection(Connection connection);
}
