package com.hongguo.code.generator.utils;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description String 工具类
 */
public class StringUtility {

    public static boolean isEmpty(String s) {
        if (s == null || s.length() <= 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static boolean isNotEmpty(String s) {
        return isEmpty(s) == Boolean.FALSE ? Boolean.TRUE : Boolean.FALSE;
    }

    public static boolean stringHasValue(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        return true;
    }

    public static String composeFullyQualifiedTableName(String catalog, String schema, String tableName, char sep) {
        StringBuilder sb = new StringBuilder();
        if (stringHasValue(catalog)) {
            sb.append(catalog).append(sep);
        }
        if (stringHasValue(schema)) {
            sb.append(schema).append(sep);
        } else {
            if (sb.length() > 0) {
                sb.append(sep);
            }
        }
        sb.append(tableName);
        return sb.toString();
    }
}
