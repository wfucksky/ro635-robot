package com.cgh.ro635bot;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class CodeGenerator {

    @Test
    public void generator() {

        String[] include = {
            // "xzt_account",
            "notice",
            "notice_param",
            //            "xzt_fbank_merchant_configuration",
            // "xzt_fbank_enterprise_account_beneficiary",
            // "xzt_fbank_enterprise_account_shareholder",
            // "xzt_fbank_merchant_configuration",
            // "xzt_fbank_order_record",
            // "xzt_fbank_personal_account",
        };

        String pathname = System.getProperty("user.dir");

        StrategyConfig strategyConfig =
                new StrategyConfig()
                        .setTablePrefix("")
                        .setNaming(NamingStrategy.underline_to_camel)
                        .setColumnNaming(NamingStrategy.underline_to_camel)
                        .setRestControllerStyle(true)
                        .setEntityLombokModel(false)
                        .setEntityBuilderModel(false)
                        .setEntitySerialVersionUID(false)
                        .setControllerMappingHyphenStyle(false)
                        .setInclude(include)
                        .setEntityTableFieldAnnotationEnable(false);

        PackageConfig packageConfig =
                new PackageConfig()
                        .setParent("com.cgh.ro635bot")
//                        .setModuleName("")
                        .setEntity("entity")
                        .setMapper("dao")
                        // .setXml("xml")
                        .setService("service")
                        .setServiceImpl("service.impl")
                        .setController("controller");
        TemplateConfig templateConfig = new TemplateConfig().setXml(null).setController(null);

        InjectionConfig injectionConfig =
                new InjectionConfig() {
                    @Override
                    public void initMap() {}
                };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        focList.add(
                new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                        return pathname
                                + "/src/main/resources/mapper/"
//                                + packageConfig.getModuleName()
//                                + "/"
                                + tableInfo.getEntityName()
                                + "Mapper"
                                + StringPool.DOT_XML;
                    }
                });
        injectionConfig.setFileOutConfigList(focList);

        generateFile(pathname, strategyConfig, packageConfig, templateConfig, injectionConfig);
    }

    /**
     * 文件生成
     *
     * @param property
     * @param strategyConfig
     * @param packageConfig
     * @param templateConfig
     */
    private void generateFile(
            String property,
            StrategyConfig strategyConfig,
            PackageConfig packageConfig,
            TemplateConfig templateConfig,
            InjectionConfig injectionConfig) {
        new AutoGenerator()
                .setGlobalConfig(
                        new GlobalConfig()
                                .setOutputDir(property + "/src/main/java")
                                .setAuthor(System.getProperty("user.name"))
                                .setFileOverride(true)
                                .setActiveRecord(true)
                                .setEnableCache(false)
                                .setOpen(false)
                                .setBaseResultMap(false)
                                .setBaseColumnList(false)
                                .setIdType(IdType.ID_WORKER)
                                .setSwagger2(false)
                                .setXmlName("%sMapper")
                                .setMapperName("%sMapper")
                                .setServiceName("%sService")
                                .setServiceImplName("%sServiceImpl")
                                .setControllerName("%sController"))
                .setDataSource(
                        new DataSourceConfig()
                                .setDbType(DbType.MYSQL)
                                .setUrl(
                                        "jdbc:mysql://127.0.0.1:3306/robot?useUnicode=true&characterEncoding=utf8&useSSL=true&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai")
                                .setDriverName("com.mysql.cj.jdbc.Driver")
                                .setUsername("robot")
                                .setPassword("Woshiwudi1101."))
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplate(templateConfig)
                .setTemplateEngine(new VelocityTemplateEngine())
                .execute();
    }
}
