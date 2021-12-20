package com.fighter.blog.code.generator;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.fighter.blog.core.exception.FighterBlogRuntimeException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: fighter-blog-serve
 * @BeloongsPackage: com.fighter.blog.code.generator
 * @ClassName: CodeGenerator
 * @CreateTime: 2021/12/21 0:16
 * @author: jie.zhang
 * @Email: 625103479@qq.com
 * @Desc: mybatis-plus 代码生成器 微服务定制版本
 */
public class CodeGenerator {

    /**
     * 数据库地址
     */
    private static final String DB_ADDRESS = CodeGeneratorConfig.DB_ADDRESS;
    /**
     * 数据库端口
     */
    private static final Integer DB_PORT = CodeGeneratorConfig.DB_PORT;
    /**
     * 数据库名称
     */
    private static final String DB_NAME = CodeGeneratorConfig.DB_NAME;
    /**
     * 数据库用户名
     */
    private static final String DB_USERNAME = CodeGeneratorConfig.DB_USERNAME;
    /**
     * 数据库密码
     */
    private static final String DB_PASSWORD = CodeGeneratorConfig.DB_PASSWORD;

    /**
     * USER_DIR
     */
    private static final String USER_DIR = "user.dir";
    /**
     * CODE_SOURCE
     */
    private static final String CODE_SOURCE = "%s/%s/%s-%s/src/main/java";
    /**
     * FILE_SEPARATOR
     */
    private static final String FILE_SEPARATOR = "/";

    /**
     * FILE_SUFFIX
     */
    private static final String FILE_SUFFIX = ".java";
    /**
     * JAVA_CODE_PATH
     */
    private static final String JAVA_CODE_PATH = "/src/main/java";
    /**
     * SERVICE_MODULE_NAME
     */
    private static final String SERVICE_MODULE_NAME = "-service";
    /**
     * API_MODULE_NAME
     */
    private static final String API_MODULE_NAME = "-api";
    /**
     * BACE_PACKAGE_PATH
     */
    private static final String BACE_PACKAGE_PATH = "/com/fighter/blog/";


    public static void main(String[] args) {

        String projectName = CodeGeneratorConfig.PRODECT_NAME;
        checkProjectExists(projectName);
        String module = CodeGeneratorConfig.MODULE;
        String tableNames = CodeGeneratorConfig.TABLE_NAMES;
        String prefix = CodeGeneratorConfig.PREFIX;
        String author = CodeGeneratorConfig.AUTHOR;
        String mysqlUrl = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC&rewriteBatchedStatements=true", DB_ADDRESS, DB_PORT, DB_NAME);

        generatorAPI(projectName, module, tableNames, prefix, author, mysqlUrl, DB_USERNAME, DB_PASSWORD);

        generatorSERVER(projectName, module, tableNames, prefix, author, mysqlUrl, DB_USERNAME, DB_PASSWORD);
    }

    /**
     * 检测项目是否存在
     *
     * @param projectName 项目名称
     */
    private static void checkProjectExists(String projectName) {

        File apiFile = new File(String.format(CODE_SOURCE, System.getProperty(USER_DIR), projectName, projectName, "api"));
        File serverFile = new File(String.format(CODE_SOURCE, System.getProperty(USER_DIR), projectName, projectName, "service"));
        if (!apiFile.exists()) {
            throw new FighterBlogRuntimeException(400, "api模块不存在!");
        }
        if (!serverFile.exists()) {
            throw new FighterBlogRuntimeException(400, "service模块不存在!");
        }

    }

    /**
     * 获取数据源配置
     *
     * @param url      数据库地址
     * @param userName 数据库用户名
     * @param password 数据库密码
     * @return 数据库配置源对象
     */
    private static DataSourceConfig getDataSourceConfig(String url, String userName, String password) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(userName);
        dsc.setPassword(password);
        return dsc;
    }


    /**
     * @param projectName  项目名称
     * @param generateType 生成类型  api or server
     * @param author       作者
     * @return 全剧配置对象
     */
    private static GlobalConfig getGlobalConfig(String projectName, String generateType, String author) {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(String.format(CODE_SOURCE, System.getProperty(USER_DIR), projectName, projectName, generateType));
        gc.setAuthor(author);
        gc.setOpen(false);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setEntityName("%s");
        gc.setXmlName("%sMapper");
        gc.setXmlName("%s");
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(true);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        return gc;
    }

    /**
     * 获取包配置信息
     *
     * @param module 模块名称
     * @return 包配置信息
     */
    private static PackageConfig getPackageConfig(String module) {
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.fighter." + module);
        pc.setService("service");
        pc.setEntity("entity");
        return pc;
    }

    /**
     * 生成API代码
     *
     * @param projectName 项目名称
     * @param module      模块名称
     * @param tableNames  表明集合
     * @param prefix      表明前缀
     * @param author      作者信息
     * @param mysqlUrl    mysql地址
     * @param userName    mysql用户名
     * @param password    mysql密码
     */
    public static void generatorAPI(String projectName, String module, String tableNames, String prefix, String author, String mysqlUrl, String userName, String password) {

        String apiPath = BACE_PACKAGE_PATH + module + FILE_SEPARATOR;
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 数据源配置
        mpg.setDataSource(getDataSourceConfig(mysqlUrl, userName, password));
        // 设置生成配置
        mpg.setGlobalConfig(getGlobalConfig(projectName, "api", author));
        // 包配置
        mpg.setPackageInfo(getPackageConfig(module));

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // service层
        focList.add(new FileOutConfig("/templates/service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                //自定义输出文件名 + pc.getModuleName()
                String expand = System.getProperty(USER_DIR) + FILE_SEPARATOR + projectName + FILE_SEPARATOR + projectName + API_MODULE_NAME + JAVA_CODE_PATH + apiPath + "service";
                return String.format((expand + File.separator + "%s" + FILE_SUFFIX), tableInfo.getServiceName());
            }
        });

        // 实体
        focList.add(new FileOutConfig("/templates/entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String expand = System.getProperty(USER_DIR) + FILE_SEPARATOR + projectName + FILE_SEPARATOR + projectName + API_MODULE_NAME + JAVA_CODE_PATH + apiPath + "entity";
                return String.format((expand + File.separator + "%s" + FILE_SUFFIX), tableInfo.getEntityName());
            }
        });

        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                checkDir(filePath);
                return true;
            }
        });


        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setController(null);
        //此处设置为null，就不会再java下创建xml的文件夹了
        templateConfig.setXml(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setController(null);
        templateConfig.setMapper(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        //表名
        strategy.setInclude(tableNames.split(","));
        strategy.setControllerMappingHyphenStyle(true);

        //根据你的表名来建对应的类名，如果你的表名没有什么下划线，比如test，那么你就可以取消这一步
        if (StringUtils.isNotBlank(prefix)) {
            strategy.setTablePrefix(prefix);
        }
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    public static void generatorSERVER(String projectName, String module, String tableNames, String prefix, String author, String mysqlUrl, String userName, String password) {
        String serverPath = BACE_PACKAGE_PATH + module + FILE_SEPARATOR;
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 数据源配置
        mpg.setDataSource(getDataSourceConfig(mysqlUrl, userName, password));
        // 全局配置
        mpg.setGlobalConfig(getGlobalConfig(projectName, "server", author));
        // 包配置
        mpg.setPackageInfo(getPackageConfig(module));

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        //控制层
        focList.add(new FileOutConfig("/templates/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String expand = System.getProperty(USER_DIR) + FILE_SEPARATOR + projectName + FILE_SEPARATOR + projectName + SERVICE_MODULE_NAME + JAVA_CODE_PATH + serverPath + "controller";
                return String.format((expand + File.separator + "%s" + FILE_SUFFIX), tableInfo.getControllerName());
            }
        });

        //services实现类
        focList.add(new FileOutConfig("/templates/serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String expand = System.getProperty(USER_DIR) + FILE_SEPARATOR + projectName + FILE_SEPARATOR + projectName + SERVICE_MODULE_NAME + JAVA_CODE_PATH + serverPath + "service/impl";
                return String.format((expand + File.separator + "%s" + FILE_SUFFIX), tableInfo.getServiceImplName());
            }
        });

        //数据层
        focList.add(new FileOutConfig("/templates/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String expand = System.getProperty(USER_DIR) + FILE_SEPARATOR + projectName + FILE_SEPARATOR + projectName + SERVICE_MODULE_NAME + JAVA_CODE_PATH + serverPath + "mapper";
                return String.format((expand + File.separator + "%s" + FILE_SUFFIX), tableInfo.getMapperName());
            }
        });

        // xml
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String expand = System.getProperty(USER_DIR) + FILE_SEPARATOR + projectName + FILE_SEPARATOR + projectName + SERVICE_MODULE_NAME + JAVA_CODE_PATH + serverPath + "mapper/xml";
                return String.format((expand + File.separator + "%s" + ".xml"), tableInfo.getMapperName());
            }
        });

        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                checkDir(filePath);
                return true;
            }
        });


        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        //此处设置为null，就不会再java下创建xml的文件夹了
        templateConfig.setXml(null);
        templateConfig.setService(null);
        templateConfig.setEntity(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        //表名
        strategy.setInclude(tableNames.split(","));
        strategy.setControllerMappingHyphenStyle(true);

        //根据你的表名来建对应的类名，如果你的表名没有什么下划线，比如test，那么你就可以取消这一步
        if (StringUtils.isNotBlank(prefix)) {
            strategy.setTablePrefix(prefix);
        }
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
