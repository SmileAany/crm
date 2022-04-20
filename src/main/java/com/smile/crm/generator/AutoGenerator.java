package com.smile.crm.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author smile
 * @Notes 逆向工程
 * @date 2022/4/20
 * @time 4:03 PM
 * @example
 * @link
 */
public class AutoGenerator {
    /***
     * 文件安装路径
     **/
    private final static String PKG_PATH = System.getProperty("user.dir") + "/src/main/java";

    /***
     * xml安装路径
     **/
    private final static String XML_PATH = System.getProperty("user.dir") + "/src/main/resources/mapper";

    /***
     * 逆向工程生成器
     **/
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/crm?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT%2B8", "root", "root")
                .globalConfig(builder -> {
                    builder.author("smile")
                            .commentDate("yyy-MM-dd")
                            .disableOpenDir()
                            .fileOverride()
                            .outputDir(PKG_PATH);
                })
                .packageConfig(builder -> {
                    builder.parent("com.smile")
                            .moduleName("crm")
                            .service("model.service")
                            .serviceImpl("model.service.impl")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,XML_PATH));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user");
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .enableLombok();
                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER);
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
