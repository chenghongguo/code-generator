package com.hongguo.code.generator.core;

import com.alibaba.fastjson.JSONObject;
import com.hongguo.code.generator.config.CodeGeneratorConfiguration;
import com.hongguo.code.generator.config.ContextConfiguration;
import lombok.Data;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
@Data
public class CodeGenerator {

    private CodeGeneratorConfiguration configuration;

    public CodeGenerator(CodeGeneratorConfiguration configuration) {
        this.configuration = configuration;
    }

    public void generator() {
        ContextConfiguration context = configuration.getContextConfiguration();
        context.introspectTables();
        System.out.println(JSONObject.toJSONString(context.getIntrospectedTables()));
    }
}
