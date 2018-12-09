package com.hongguo.code.generator.core.db;

import lombok.Data;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
@Data
public class IntrospectedColumn {

    private String columnName;

    private String columnType;

    private int scale;

    private int length;

    private boolean isPrimaryKey;

    private String defaultValue;

    private boolean nullable;

    public IntrospectedColumn() {
        this.nullable = true;
        this.defaultValue = "";
    }
}
