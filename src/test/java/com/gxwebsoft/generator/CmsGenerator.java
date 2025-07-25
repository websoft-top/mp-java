package com.gxwebsoft.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.gxwebsoft.generator.engine.BeetlTemplateEnginePlus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CMS模块-代码生成工具
 *
 * @author WebSoft
 * @since 2021-09-05 00:31:14
 */
public class CmsGenerator {
  // 输出位置
  private static final String OUTPUT_LOCATION = System.getProperty("user.dir");
  //private static final String OUTPUT_LOCATION = "D:/codegen";  // 不想生成到项目中可以写磁盘路径
  // 输出目录
  private static final String OUTPUT_DIR = "/src/main/java";
  // Vue文件输出位置
  private static final String OUTPUT_LOCATION_VUE = "/Users/gxwebsoft/VUE/site";
  // Vue文件输出目录
  private static final String OUTPUT_LOCATION_UNIAPP = "/Users/gxwebsoft/APP/site";
  // Vue文件输出目录
  private static final String OUTPUT_DIR_VUE = "/src";
  // 作者名称
  private static final String AUTHOR = "科技小王子";
  // 是否在xml中添加二级缓存配置
  private static final boolean ENABLE_CACHE = false;
  // 数据库连接配置 - 请根据实际环境修改
  private static final String DB_URL = System.getProperty("db.url", "jdbc:mysql://localhost:3306/modules?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8");
  private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
  private static final String DB_USERNAME = System.getProperty("db.username", "root");
  private static final String DB_PASSWORD = System.getProperty("db.password", "password");
  // 包名
  private static final String PACKAGE_NAME = "com.gxwebsoft";
  // 模块名
  private static final String MODULE_NAME = "cms";
  // 需要生成的表
  private static final String[] TABLE_NAMES = new String[]{
//            "cms_article",
//            "cms_article_category",
//            "cms_article_like",
//            "cms_article_comment",
//              "cms_article_content",
//            "cms_docs",
//                  "cms_docs_book",
//                  "cms_docs_content",
//            "cms_ad",
//            "cms_ad_record",
//            "cms_navigation",
//            "cms_design",
//            "cms_design_record",
//            "cms_website",
//            "cms_website_field",
//              "cms_form",
//              "cms_form_record",
//              "cms_domain",
//              "cms_mp_menu"
//    "cms_mp_pages",
//    "cms_mp"
//    "cms_mp_field"
//      "cms_mp_ad"
//    "cms_components"
//              "cms_mp_official_menu",
//    "cms_order",
//      "cms_model"
//    "cms_lang",
//    "cms_lang_log",
//    "cms_website_setting"

  };
  // 需要去除的表前缀
  private static final String[] TABLE_PREFIX = new String[]{
    "tb_"
  };
  // 不需要作为查询参数的字段
  private static final String[] PARAM_EXCLUDE_FIELDS = new String[]{
    "tenant_id",
    "create_time",
    "update_time"
  };
  // 查询参数使用String的类型
  private static final String[] PARAM_TO_STRING_TYPE = new String[]{
    "Date",
    "LocalDate",
    "LocalTime",
    "LocalDateTime"
  };
  // 查询参数使用EQ的类型
  private static final String[] PARAM_EQ_TYPE = new String[]{
    "Integer",
    "Boolean",
    "BigDecimal"
  };
  // 是否添加权限注解
  private static final boolean AUTH_ANNOTATION = true;
  // 是否添加日志注解
  private static final boolean LOG_ANNOTATION = true;
  // controller的mapping前缀
  private static final String CONTROLLER_MAPPING_PREFIX = "/api";
  // 模板所在位置
  private static final String TEMPLATES_DIR = "/src/test/java/com/gxwebsoft/generator/templates";

  public static void main(String[] args) {
    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    gc.setOutputDir(OUTPUT_LOCATION + OUTPUT_DIR);
    gc.setAuthor(AUTHOR);
    gc.setOpen(false);
    gc.setFileOverride(true);
    gc.setEnableCache(ENABLE_CACHE);
    gc.setSwagger2(true);
    gc.setIdType(IdType.AUTO);
    gc.setServiceName("%sService");
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl(DB_URL);
    // dsc.setSchemaName("public");
    dsc.setDriverName(DB_DRIVER);
    dsc.setUsername(DB_USERNAME);
    dsc.setPassword(DB_PASSWORD);
    mpg.setDataSource(dsc);

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setModuleName(MODULE_NAME);
    pc.setParent(PACKAGE_NAME);
    mpg.setPackageInfo(pc);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    strategy.setInclude(TABLE_NAMES);
    strategy.setTablePrefix(TABLE_PREFIX);
    strategy.setSuperControllerClass(PACKAGE_NAME + ".common.core.web.BaseController");
    strategy.setEntityLombokModel(true);
    strategy.setRestControllerStyle(true);
    strategy.setControllerMappingHyphenStyle(true);
    strategy.setLogicDeleteFieldName("deleted");
    mpg.setStrategy(strategy);

    // 模板配置
    TemplateConfig templateConfig = new TemplateConfig();
    templateConfig.setController(TEMPLATES_DIR + "/controller.java");
    templateConfig.setEntity(TEMPLATES_DIR + "/entity.java");
    templateConfig.setMapper(TEMPLATES_DIR + "/mapper.java");
    templateConfig.setXml(TEMPLATES_DIR + "/mapper.xml");
    templateConfig.setService(TEMPLATES_DIR + "/service.java");
    templateConfig.setServiceImpl(TEMPLATES_DIR + "/serviceImpl.java");
    mpg.setTemplate(templateConfig);
    mpg.setTemplateEngine(new BeetlTemplateEnginePlus());

    // 自定义模板配置
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("packageName", PACKAGE_NAME);
        map.put("paramExcludeFields", PARAM_EXCLUDE_FIELDS);
        map.put("paramToStringType", PARAM_TO_STRING_TYPE);
        map.put("paramEqType", PARAM_EQ_TYPE);
        map.put("authAnnotation", AUTH_ANNOTATION);
        map.put("logAnnotation", LOG_ANNOTATION);
        map.put("controllerMappingPrefix", CONTROLLER_MAPPING_PREFIX);
        this.setMap(map);
      }
    };
    String templatePath = TEMPLATES_DIR + "/param.java.btl";
    List<FileOutConfig> focList = new ArrayList<>();
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return OUTPUT_LOCATION + OUTPUT_DIR + "/"
          + PACKAGE_NAME.replace(".", "/")
          + "/" + pc.getModuleName() + "/param/"
          + tableInfo.getEntityName() + "Param" + StringPool.DOT_JAVA;
      }
    });
    /**
     * 以下是生成VUE项目代码
     * 生成文件的路径 /api/shop/goods/index.ts
     */
    templatePath = TEMPLATES_DIR + "/index.ts.btl";

    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return OUTPUT_LOCATION_VUE + OUTPUT_DIR_VUE
          + "/api/" + pc.getModuleName() + "/"
          + tableInfo.getEntityPath() + "/" + "index.ts";
      }
    });
    focList.add(new FileOutConfig() {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return OUTPUT_LOCATION_UNIAPP + OUTPUT_DIR_VUE
          + "/api/" + pc.getModuleName() + "/"
          + tableInfo.getEntityPath() + "/" + "index.ts";
      }
    });
    // 生成TS文件 (/api/shop/goods/model/index.ts)
    templatePath = TEMPLATES_DIR + "/model.ts.btl";
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return OUTPUT_LOCATION_VUE + OUTPUT_DIR_VUE
          + "/api/" + pc.getModuleName() + "/"
          + tableInfo.getEntityPath() + "/model/" + "index.ts";
      }
    });
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return OUTPUT_LOCATION_UNIAPP + OUTPUT_DIR_VUE
          + "/api/" + pc.getModuleName() + "/"
          + tableInfo.getEntityPath() + "/model/" + "index.ts";
      }
    });
    // 生成Vue文件(/views/shop/goods/index.vue)
    templatePath = TEMPLATES_DIR + "/index.vue.btl";
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return OUTPUT_LOCATION_VUE + OUTPUT_DIR_VUE
          + "/views/" + pc.getModuleName() + "/"
          + tableInfo.getEntityPath() + "/" + "index.vue";
      }
    });

    // 生成components文件(/views/shop/goods/components/edit.vue)
    templatePath = TEMPLATES_DIR + "/components.edit.vue.btl";
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return OUTPUT_LOCATION_VUE + OUTPUT_DIR_VUE
          + "/views/" + pc.getModuleName() + "/"
          + tableInfo.getEntityPath() + "/components/" + tableInfo.getEntityPath() + "Edit.vue";
      }
    });

    // 生成components文件(/views/shop/goods/components/search.vue)
    templatePath = TEMPLATES_DIR + "/components.search.vue.btl";
    focList.add(new FileOutConfig(templatePath) {
      @Override
      public String outputFile(TableInfo tableInfo) {
        return OUTPUT_LOCATION_VUE + OUTPUT_DIR_VUE
          + "/views/" + pc.getModuleName() + "/"
          + tableInfo.getEntityPath() + "/components/" + "search.vue";
      }
    });

    cfg.setFileOutConfigList(focList);
    mpg.setCfg(cfg);

    mpg.execute();
  }

}
