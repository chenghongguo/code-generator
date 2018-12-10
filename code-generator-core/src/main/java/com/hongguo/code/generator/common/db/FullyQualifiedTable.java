package com.hongguo.code.generator.common.db;

import com.hongguo.code.generator.config.ContextConfiguration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongguo_cheng
 * @date 2018-12-10
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullyQualifiedTable {
    private String catalog;
    private String schema;
    private String tableName;
    private String domainName;
    private ContextConfiguration context;
}
