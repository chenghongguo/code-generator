package com.hongguo.code.generator.api;

import com.hongguo.code.generator.config.ContextConfiguration;

/**
 * @author hongguo_cheng
 * @date 2018-12-12
 * @description
 */
public interface JavaFormatter {

    void setContext(ContextConfiguration context);

    String getFormattedContent(CompilationUnit compilationUnit);
}
