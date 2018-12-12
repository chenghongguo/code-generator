package com.hongguo.code.generator.api;

/**
 * @author hongguo_cheng
 * @date 2018-12-12
 * @description
 */
public class GeneratedXmlFile extends GeneratedFile {

    private String targetPackage;

    private String targetProject;

    public GeneratedXmlFile(String targetProject, String targetPackage) {
        super(targetProject);
        this.targetPackage = targetPackage;
    }

    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public String getFormattedContent() {
        return null;
    }

    @Override
    public String getTargetPackage() {
        return this.targetPackage;
    }
}
