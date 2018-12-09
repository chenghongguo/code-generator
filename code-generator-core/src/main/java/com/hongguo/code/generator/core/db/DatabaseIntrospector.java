package com.hongguo.code.generator.core.db;

import com.hongguo.code.generator.config.ContextConfiguration;
import com.hongguo.code.generator.config.TableConfiguration;
import lombok.Data;

import java.sql.DatabaseMetaData;
import java.util.List;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
@Data
public class DatabaseIntrospector {

    private DatabaseMetaData databaseMetaData;

    private ContextConfiguration context;

    public DatabaseIntrospector(DatabaseMetaData databaseMetaData, ContextConfiguration context) {
        this.databaseMetaData = databaseMetaData;
        this.context = context;
    }

    public List<IntrospectedTable> introspectTables(TableConfiguration tableConfiguration) {
        return null;
    }
}
