package com.hongguo.code.generator.config;

import com.alibaba.fastjson.JSONObject;
import com.hongguo.code.generator.config.parser.ConfigurationParser;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description 单元测试类
 */
public class CodeGeneratorConfigurationTests {

    @Test
    public void testConfigFile() {
        InputStream inputStream = this.getClass().getResourceAsStream("/codeGenConfig.xml");
        ConfigurationParser parser = new ConfigurationParser();
        CodeGeneratorConfiguration configuration = parser.parseConfiguration(inputStream);
        System.out.println(JSONObject.toJSONString(configuration));
    }
}
