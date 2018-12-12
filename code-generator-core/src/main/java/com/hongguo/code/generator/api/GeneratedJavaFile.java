package com.hongguo.code.generator.api;

/**
 * @author hongguo_cheng
 * @date 2018-12-12
 * @description
 */
public class GeneratedJavaFile extends GeneratedFile {

    private String fileEncoding;

    private CompilationUnit compilationUnit;

    private JavaFormatter javaFormatter;

    public GeneratedJavaFile(String targetPackage, String fileEncoding,
                             CompilationUnit compilationUnit,
                             JavaFormatter javaFormatter) {
        super(targetPackage);
        this.fileEncoding = fileEncoding;
        this.compilationUnit = compilationUnit;
        this.javaFormatter = javaFormatter;
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
        return null;
    }
}
