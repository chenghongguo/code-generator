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
}
