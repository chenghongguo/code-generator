package com.hongguo.code.generator.config;

import lombok.Data;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
@Data
public class JdbcConnectionConfiguration {

    private String driverClass;

    private String url;

    private String username;

    private String password;
}
