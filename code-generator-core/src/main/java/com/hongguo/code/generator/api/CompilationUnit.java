package com.hongguo.code.generator.api;

import java.util.List;

/**
 * @author hongguo_cheng
 * @date 2018-12-12
 * @description
 */
public interface CompilationUnit {

    String getFormattedContent();

    boolean isJavaInterface();

    boolean isJavaEnumeration();

    void addFileCommentLine(String commentLine);

    List<String> getFileCommentLines();

    FullyQualifiedJavaType getType();
}
