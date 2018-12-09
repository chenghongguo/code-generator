package com.hongguo.code.generator.config.parser;

import com.hongguo.code.generator.config.CodeGeneratorConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description 配置文件解析器
 */
public class ConfigurationParser {

    /**
     * 解析文件
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public CodeGeneratorConfiguration parseConfiguration(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("can not found config file");
        }

        InputStream inputStream = new FileInputStream(file);

        return parseConfiguration(inputStream);
    }

    /**
     * 解析流
     *
     * @param inputStream
     * @return
     */
    public CodeGeneratorConfiguration parseConfiguration(InputStream inputStream) {
        CodeGeneratorConfiguration configuration = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            Element rootNode = document.getDocumentElement();

            if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
                configuration = parseMyBatisConfiguration(rootNode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration;
    }

    /**
     * 解析配置文件根节点
     *
     * @param rootNode
     * @return
     */
    private CodeGeneratorConfiguration parseMyBatisConfiguration(Element rootNode) {
        CodeGeneratorConfigurationParser parse = new CodeGeneratorConfigurationParser();
        return parse.parseConfig(rootNode);
    }
}
