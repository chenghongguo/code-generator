package com.hongguo.code.generator.common.db;

import com.hongguo.code.generator.config.ContextConfiguration;
import lombok.Data;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
@Data
public class IntrospectedColumn {

    protected String columnName;

    protected String typeName;

    protected Integer jdbcType;

    protected int scale;

    protected int length;

    protected boolean isPrimaryKey;

    protected String defaultValue;

    protected boolean nullable;

    protected String remarks;

    protected boolean isAutoIncrement;

    protected boolean isGeneratedColumn;

    protected ContextConfiguration context;

    protected IntrospectedTable introspectedTable;

    public IntrospectedColumn() {
        this.nullable = true;
        this.defaultValue = "";
    }
}
