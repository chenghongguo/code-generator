package com.hongguo.code.generator.core;

import com.alibaba.fastjson.JSONObject;
import com.hongguo.code.generator.api.GeneratedJavaFile;
import com.hongguo.code.generator.api.GeneratedXmlFile;
import com.hongguo.code.generator.config.CodeGeneratorConfiguration;
import com.hongguo.code.generator.config.ContextConfiguration;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
@Data
public class CodeGenerator {

    private CodeGeneratorConfiguration configuration;

    private List<GeneratedJavaFile> generatedJavaFiles;

    private List<GeneratedXmlFile> generatedXmlFiles;

    public CodeGenerator(CodeGeneratorConfiguration configuration) {
        this.configuration = configuration;
        this.generatedJavaFiles = new ArrayList<>();
        this.generatedXmlFiles = new ArrayList<>();
    }

    public void generator() {
        generatedJavaFiles.clear();
        generatedXmlFiles.clear();
        ContextConfiguration context = configuration.getContextConfiguration();
        context.introspectTables();
        System.out.println(JSONObject.toJSONString(context.getIntrospectedTables()));
    }
}
