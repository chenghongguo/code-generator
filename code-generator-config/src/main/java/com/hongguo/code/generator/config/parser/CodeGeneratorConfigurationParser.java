package com.hongguo.code.generator.config.parser;

import com.hongguo.code.generator.config.*;
import com.hongguo.code.generator.utils.StringUtility;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.security.InvalidParameterException;

/**
 * @author hongguo_cheng
 * @date 2018-12-09
 * @description 配置文件解析器
 */
public class CodeGeneratorConfigurationParser {

    /**
     * 解析configuration标签
     *
     * @param rootNode
     * @return
     */
    public CodeGeneratorConfiguration parseConfig(Element rootNode) {
        CodeGeneratorConfiguration configuration = new CodeGeneratorConfiguration();
        NodeList childNodes = rootNode.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if ("context".equalsIgnoreCase(childNode.getNodeName())) {
                parseContextConfig(configuration, childNode);
            }
        }
        return configuration;
    }

    /**
     * 解析context标签
     *
     * @param configuration
     * @param rootNode
     */
    private void parseContextConfig(CodeGeneratorConfiguration configuration, Node rootNode) {
        ContextConfiguration context = new ContextConfiguration();
        configuration.setContextConfiguration(context);

        NodeList childNodes = rootNode.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);

            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            String nodeName = childNode.getNodeName();
            if ("jdbcConnection".equalsIgnoreCase(nodeName)) {
                parseJdbcConnection(context, childNode);
            } else if ("javaModelGenerator".equalsIgnoreCase(nodeName)) {
                parseJavaModelGenerator(context, childNode);
            } else if ("sqlMapGenerator".equalsIgnoreCase(nodeName)) {
                parseSqlMapGenerator(context, childNode);
            } else if ("table".equalsIgnoreCase(nodeName)) {
                parseTable(context, childNode);
            }
        }
    }

    public void parseJdbcConnection(ContextConfiguration context, Node rootNode) {

        NamedNodeMap attributes = rootNode.getAttributes();
        Node driverClassNode = attributes.getNamedItem("driverClass");
        String driverClass = driverClassNode.getNodeValue();
        if (StringUtility.isEmpty(driverClass)) {
            throw new InvalidParameterException("driverClass is invalid");
        }
        Node urlNode = attributes.getNamedItem("url");
        String url = urlNode.getNodeValue();
        if (StringUtility.isEmpty(url)) {
            throw new InvalidParameterException("url is invalid");
        }
        Node usernameNode = attributes.getNamedItem("username");
        String username = usernameNode.getNodeValue();
        if (StringUtility.isEmpty(username)) {
            username = "root";
        }
        Node passwordNode = attributes.getNamedItem("password");
        String password = passwordNode.getNodeValue();
        if (StringUtility.isEmpty(password)) {
            password = "2208122chs";
        }

        JdbcConnectionConfiguration config = new JdbcConnectionConfiguration();
        config.setDriverClass(driverClass);
        config.setUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        context.setJdbcConnectionConfiguration(config);
    }

    private void parseJavaModelGenerator(ContextConfiguration context, Node rootNode) {
        NamedNodeMap attributes = rootNode.getAttributes();
        Node targetPackageNode = attributes.getNamedItem("targetPackage");
        String targetPackage = targetPackageNode.getNodeValue();
        Node targetProjectNode = attributes.getNamedItem("targetProject");
        String targetProject = targetProjectNode.getNodeValue();

        JavaModelConfiguration config = new JavaModelConfiguration();
        config.setTargetPackage(targetPackage);
        config.setTargetProject(targetProject);

        context.setJavaModelConfiguration(config);
    }

    private void parseSqlMapGenerator(ContextConfiguration context, Node rootNode) {
        NamedNodeMap attributes = rootNode.getAttributes();
        Node targetPackageNode = attributes.getNamedItem("targetPackage");
        String targetPackage = targetPackageNode.getNodeValue();
        Node targetProjectNode = attributes.getNamedItem("targetProject");
        String targetProject = targetProjectNode.getNodeValue();

        SqlMapperConfiguration config = new SqlMapperConfiguration();
        config.setTargetPackage(targetPackage);
        config.setTargetProject(targetProject);

        context.setSqlMapperConfiguration(config);
    }

    private void parseTable(ContextConfiguration context, Node rootNode) {
        NamedNodeMap attributes = rootNode.getAttributes();
        Node tableNameNode = attributes.getNamedItem("tableName");
        String tableName = tableNameNode.getNodeValue();
        Node domainNameNode = attributes.getNamedItem("domainName");
        String domainName = domainNameNode.getNodeValue();

        TableConfiguration config = new TableConfiguration();
        config.setDomainName(domainName);
        config.setTableName(tableName);

        context.addTableConfiguration(config);
    }
}
