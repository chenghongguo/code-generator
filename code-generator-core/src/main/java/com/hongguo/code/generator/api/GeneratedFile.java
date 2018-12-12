package com.hongguo.code.generator.api;

/**
 * @author hongguo_cheng
 * @date 2018-12-12
 * @description
 */
public abstract class GeneratedFile {

    private String targetProject;

    public GeneratedFile(String targetProject) {
        this.targetProject = targetProject;
    }

    public abstract String getFileName();

    public abstract String getFormattedContent();

    public abstract String getTargetPackage();

    public String getTargetProject() {
        return this.targetProject;
    }

    @Override
    public String toString() {
        return this.getFormattedContent();
    }

}
