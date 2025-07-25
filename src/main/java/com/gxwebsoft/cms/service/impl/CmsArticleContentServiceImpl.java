package com.gxwebsoft.cms.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.entity.*;
import com.gxwebsoft.cms.mapper.CmsArticleContentMapper;
import com.gxwebsoft.cms.service.CmsArticleContentService;
import com.gxwebsoft.cms.param.CmsArticleContentParam;
import com.gxwebsoft.cms.service.CmsArticleService;
import com.gxwebsoft.cms.service.CmsLangLogService;
import com.gxwebsoft.cms.service.CmsNavigationService;
import com.gxwebsoft.common.core.utils.AliYunSender;
import com.gxwebsoft.common.core.utils.JSONUtil;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章记录表Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsArticleContentServiceImpl extends ServiceImpl<CmsArticleContentMapper, CmsArticleContent> implements CmsArticleContentService {
    @Resource
    private CmsNavigationService cmsNavigationService;
    @Resource
    private CmsArticleService cmsArticleService;
    @Resource
    private CmsArticleContentService cmsArticleContentService;
    @Resource
    private CmsLangLogService cmsLangLogService;
    @Override
    public PageResult<CmsArticleContent> pageRel(CmsArticleContentParam param) {
        PageParam<CmsArticleContent, CmsArticleContentParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsArticleContent> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsArticleContent> listRel(CmsArticleContentParam param) {
        List<CmsArticleContent> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsArticleContent, CmsArticleContentParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsArticleContent getByIdRel(Integer id) {
        CmsArticleContentParam param = new CmsArticleContentParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Override
    public void translate(CmsArticle article) {
      // 未开启多语言
      if(cmsLangLogService.count() == 0){
        return;
      }
      // 仅限定新增简体中文才会同步翻译其他目标语言
      if(!article.getLang().equals("zh_CN")){
        return;
      }
      // 是否启用自动翻译
      if(!article.getTranslation()){
        return;
      }
      // 查询关联的默认语言栏目ID
      CmsNavigation navigation = cmsNavigationService.getOne(new LambdaQueryWrapper<CmsNavigation>().eq(CmsNavigation::getLangCategoryId, article.getCategoryId()).last("limit 1"));
      if (ObjectUtil.isNotEmpty(navigation)) {
        TranslateDataVo vo = new TranslateDataVo();
        vo.setFormatType("text");
        vo.setSourceLanguage("auto");
        vo.setTargetLanguage("en");

        // 翻译标题
        vo.setScene("title");
        vo.setSourceText(article.getTitle());
        article.setTitle(getTranslateApi(vo));

        // 翻译摘要
        vo.setSourceText(article.getComments());
        vo.setScene("description");
        article.setComments(getTranslateApi(vo));

        // 翻译产品概述
        vo.setSourceText(article.getOverview());
        article.setOverview(getTranslateApi(vo));

        // 翻译关键词
        vo.setScene("title");
        vo.setSourceText(article.getTags());
        article.setTags(getTranslateApi(vo));

        // 翻译话题
        vo.setScene("title");
        vo.setSourceText(article.getTopic());
        article.setTopic(getTranslateApi(vo));

        // 翻译来源
        vo.setScene("title");
        vo.setSourceText(article.getSource());
        article.setSource(getTranslateApi(vo));

        // 翻译内容
        vo.setScene(null);
        vo.setSourceText(article.getContent());
        article.setContent(getTranslateApi(vo));

        // 其他参数
        article.setLang("en");
        article.setCategoryId(navigation.getNavigationId());


        CmsArticle target = cmsArticleService.getOne(new LambdaQueryWrapper<CmsArticle>().eq(CmsArticle::getLangArticleId,article.getArticleId()).last("limit 1"));
        if(article.getIsUpdate() != null && target != null){
          // 更新操作
          if (ObjectUtil.isNotEmpty(target)) {
            target.setTitle(article.getTitle());
            target.setImage(article.getImage());
            target.setFiles(article.getFiles());
            target.setComments(article.getComments());
            target.setTags(article.getTags());
            target.setRecommend(article.getRecommend());
            target.setOverview(article.getOverview());
            target.setContent(article.getContent());
            System.out.println("target = " + target);
            cmsArticleService.updateById(target);
            cmsArticleContentService.update(new LambdaUpdateWrapper<CmsArticleContent>().eq(CmsArticleContent::getArticleId, target.getArticleId()).set(CmsArticleContent::getContent,target.getContent()));
          }
        }else {
          // 新增操作
          article.setLangArticleId(article.getArticleId());
          cmsArticleService.save(article);
          final CmsArticleContent content = new CmsArticleContent();
          content.setArticleId(article.getArticleId());
          content.setContent(article.getContent());
          content.setTenantId(article.getTenantId());
          cmsArticleContentService.save(content);
        }
      }

    }

    /**
     * 机器翻译
     * 阿里云接口
     * <a href="https://help.aliyun.com/zh/machine-translation/developer-reference/using-rest-api?spm=a2c4g.11186623.help-menu-30396.d_4_3_0.7c071674A2aU4c">...</a>
     */
    public String getTranslateApi(TranslateDataVo item){
      String serviceURL = "http://mt.cn-hangzhou.aliyuncs.com/api/translate/web/ecommerce";
      // 从环境变量或配置中获取阿里云密钥
      String accessKeyId = System.getProperty("aliyun.translate.accessKeyId", "");
      String accessKeySecret = System.getProperty("aliyun.translate.accessKeySecret", "");

      final Map<String, Object> map = new HashMap<>();
      map.put("FormatType","text");
      map.put("SourceLanguage","auto");
      map.put("TargetLanguage","en");
      map.put("SourceText",item.getSourceText());
      map.put("Scene","description");
      map.put("Context","产品介绍");
      // Sender代码请参考帮助文档“签名方法”
      String result =  AliYunSender.sendPost(serviceURL, JSONUtil.toJSONString(map), accessKeyId, accessKeySecret);
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
