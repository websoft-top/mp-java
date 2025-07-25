package com.gxwebsoft.cms.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.entity.CmsNavigation;
import com.gxwebsoft.cms.entity.TranslateDataVo;
import com.gxwebsoft.cms.mapper.CmsDesignMapper;
import com.gxwebsoft.cms.service.CmsArticleContentService;
import com.gxwebsoft.cms.service.CmsDesignService;
import com.gxwebsoft.cms.entity.CmsDesign;
import com.gxwebsoft.cms.param.CmsDesignParam;
import com.gxwebsoft.cms.service.CmsLangLogService;
import com.gxwebsoft.cms.service.CmsNavigationService;
import com.gxwebsoft.common.core.utils.AliYunSender;
import com.gxwebsoft.common.core.utils.JSONUtil;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页面管理记录表Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsDesignServiceImpl extends ServiceImpl<CmsDesignMapper, CmsDesign> implements CmsDesignService {
  @Resource
  private CmsLangLogService cmsLangLogService;
  @Resource
  private CmsNavigationService cmsNavigationService;
  @Resource
  private CmsArticleContentService cmsArticleContentService;

  @Override
  public PageResult<CmsDesign> pageRel(CmsDesignParam param) {
    PageParam<CmsDesign, CmsDesignParam> page = new PageParam<>(param);
    page.setDefaultOrder("sort_number asc, create_time asc");
    List<CmsDesign> list = baseMapper.selectPageRel(page, param);
    return new PageResult<>(list, page.getTotal());
  }

  @Override
  public List<CmsDesign> listRel(CmsDesignParam param) {
    List<CmsDesign> list = baseMapper.selectListRel(param);
    // 排序
    PageParam<CmsDesign, CmsDesignParam> page = new PageParam<>();
    page.setDefaultOrder("sort_number asc, create_time asc");
    return page.sortRecords(list);
  }

  @Override
  public CmsDesign getByIdRel(Integer pageId) {
    CmsDesignParam param = new CmsDesignParam();
    param.setPageId(pageId);
    return param.getOne(baseMapper.selectListRel(param));
  }

  @Override
  public void translate(CmsDesign cmsDesign) {
    // 是否启用自动翻译
    if (!cmsDesign.getTranslation()) {
      return;
    }
    // 未开启多语言
    if (cmsLangLogService.count() == 0) {
      return;
    }
    // 查询关联的默认语言栏目ID
    CmsNavigation navigation = cmsNavigationService.getOne(new LambdaQueryWrapper<CmsNavigation>().eq(CmsNavigation::getLangCategoryId, cmsDesign.getCategoryId()).last("limit 1"));
    if (ObjectUtil.isEmpty(navigation)) {
      return;
    }
    // 仅限定新增简体中文才会同步翻译其他目标语言
    if (!navigation.getLang().equals("en")) {
      return;
    }
    // 查找要翻译的单页面信息
    final CmsDesign design = getOne(new LambdaQueryWrapper<CmsDesign>().eq(CmsDesign::getCategoryId, navigation.getNavigationId()).last("limit 1"));
    if (ObjectUtil.isNotEmpty(design)) {

      TranslateDataVo vo = new TranslateDataVo();
      vo.setFormatType("text");
      vo.setSourceLanguage("auto");
      vo.setTargetLanguage("en");

      // 翻译标题
      vo.setScene("title");
      vo.setSourceText(cmsDesign.getName());
      design.setName(getTranslateApi(vo));

      // 翻译关键词
      vo.setSourceText(cmsDesign.getKeywords());
      design.setKeywords(getTranslateApi(vo));

      // 翻译描述
      vo.setSourceText(cmsDesign.getDescription());
      design.setDescription(getTranslateApi(vo));

      // 翻译页面内容
      vo.setScene(null);
      vo.setSourceText(cmsDesign.getContent());
      design.setContent(getTranslateApi(vo));
      design.setShowBanner(cmsDesign.getShowBanner());
      design.setStyle(cmsDesign.getStyle());
      design.setShowButton(cmsDesign.getShowButton());
      design.setBuyUrl(cmsDesign.getBuyUrl());
      updateById(design);
    }
  }

  /**
   * 机器翻译
   * 阿里云接口
   * <a href="https://help.aliyun.com/zh/machine-translation/developer-reference/using-rest-api?spm=a2c4g.11186623.help-menu-30396.d_4_3_0.7c071674A2aU4c">...</a>
   */
  public String getTranslateApi(TranslateDataVo item) {
    String serviceURL = "http://mt.cn-hangzhou.aliyuncs.com/api/translate/web/ecommerce";
    // 从环境变量或配置中获取阿里云密钥
    String accessKeyId = System.getProperty("aliyun.translate.accessKeyId", "");
    String accessKeySecret = System.getProperty("aliyun.translate.accessKeySecret", "");

    final Map<String, Object> map = new HashMap<>();
    map.put("FormatType", "text");
    map.put("SourceLanguage", "auto");
    map.put("TargetLanguage", "en");
    map.put("SourceText", item.getSourceText());
    map.put("Scene", "description");
    map.put("Context", "产品介绍");
    // Sender代码请参考帮助文档“签名方法”
    String result = AliYunSender.sendPost(serviceURL, JSONUtil.toJSONString(map), accessKeyId, accessKeySecret);
    JSONObject jsonObject = JSON.parseObject(result);
    final Object code = jsonObject.get("Code");
    if (code.equals("200")) {
      final Object data = jsonObject.get("Data");
      JSONObject data1 = JSON.parseObject(data.toString());
      final Object translated = data1.get("Translated");
      final Object wordCount = data1.get("WordCount");
      return translated.toString();
    }
    return "";
  }

}
