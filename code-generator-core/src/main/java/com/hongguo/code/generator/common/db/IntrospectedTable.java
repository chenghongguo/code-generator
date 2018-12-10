package com.hongguo.code.generator.common.db;

import com.hongguo.code.generator.config.ContextConfiguration;
import com.hongguo.code.generator.config.TableConfiguration;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
@Data
public class IntrospectedTable {

    protected TableConfiguration tableConfiguration;

    protected FullyQualifiedTable fullyQualifiedTable;

    protected String tableName;

    protected List<IntrospectedColumn> baseColumns;

    protected List<IntrospectedColumn> primaryKeyColumns;

    protected ContextConfiguration context;

    protected String domainName;

    public IntrospectedTable() {
        this.baseColumns = new ArrayList<>();
        this.primaryKeyColumns = new ArrayList<>();
    }

    public void addColumn(IntrospectedColumn introspectedColumn) {
        baseColumns.add(introspectedColumn);
        introspectedColumn.setIntrospectedTable(this);
    }

    public void addPrimaryKey(String columnName) {
        Iterator<IntrospectedColumn> iter = baseColumns.iterator();
        while (iter.hasNext()) {
            IntrospectedColumn introspectedColumn = iter.next();
            if (introspectedColumn.getColumnName().equals(columnName)) {
                primaryKeyColumns.add(introspectedColumn);
                iter.remove();
                break;
            }
        }
    }
}
