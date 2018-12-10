package com.hongguo.code.generator.common.db;

import com.hongguo.code.generator.config.ContextConfiguration;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
@Data
public class IntrospectedTable {

    private String tableName;

    private List<IntrospectedColumn> baseColumns;

    private List<IntrospectedColumn> primaryKeyColumns;

    private ContextConfiguration context;

    private String domainName;

    public IntrospectedTable() {
        this.baseColumns = new ArrayList<>();
        this.primaryKeyColumns = new ArrayList<>();
    }
}
