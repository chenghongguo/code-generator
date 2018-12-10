package com.hongguo.code.generator.core;

import com.hongguo.code.generator.config.CodeGeneratorConfiguration;
import com.hongguo.code.generator.config.parser.ConfigurationParser;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description
 */
public class CodeGeneratorTest {

    @Test
    public void testCodeGenerator() {
        InputStream inputStream = this.getClass().getResourceAsStream("/codeGenConfig.xml");
        ConfigurationParser parser = new ConfigurationParser();
        CodeGeneratorConfiguration configuration = parser.parseConfiguration(inputStream);
        // System.out.println(JSONObject.toJSONString(configuration));
        CodeGenerator generator = new CodeGenerator(configuration);
        generator.generator();
    }
}
