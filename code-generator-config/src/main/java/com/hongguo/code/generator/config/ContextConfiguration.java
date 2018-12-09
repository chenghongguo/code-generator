package com.hongguo.code.generator.config;

import lombok.Data;

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

    public ContextConfiguration() {
        tableConfigurations = new ArrayList<>();
    }

    public void addTableConfiguration(TableConfiguration tc) {
        tableConfigurations.add(tc);
    }
}
