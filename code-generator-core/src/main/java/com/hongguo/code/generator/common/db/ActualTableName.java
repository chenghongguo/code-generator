package com.hongguo.code.generator.common.db;

import com.hongguo.code.generator.utils.StringUtility;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author hongguo_cheng
 * @date 2018-12-10
 * @description
 */
@Data
@NoArgsConstructor
public class ActualTableName {

    private String catalog;

    private String schema;

    private String tableName;

    private String fullName;

    public ActualTableName(String catalog, String schema, String tableName) {
        this.catalog = catalog;
        this.schema = schema;
        this.tableName = tableName;
        this.fullName = StringUtility.composeFullyQualifiedTableName(catalog, schema, tableName, '.');
    }

    @Override
    public String toString() {
        return this.fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActualTableName)) {
            return false;
        }
        ActualTableName that = (ActualTableName) o;
        return fullName.equals(that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }
}
