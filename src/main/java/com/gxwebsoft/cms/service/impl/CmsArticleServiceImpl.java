package com.gxwebsoft.cms.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.entity.CmsArticleContent;
import com.gxwebsoft.cms.entity.CmsModel;
import com.gxwebsoft.cms.entity.CmsNavigation;
import com.gxwebsoft.cms.mapper.CmsArticleMapper;
import com.gxwebsoft.cms.service.CmsArticleContentService;
import com.gxwebsoft.cms.service.CmsArticleService;
import com.gxwebsoft.cms.entity.CmsArticle;
import com.gxwebsoft.cms.param.CmsArticleParam;
import com.gxwebsoft.cms.service.CmsModelService;
import com.gxwebsoft.cms.service.CmsNavigationService;
import com.gxwebsoft.common.core.utils.JSONUtil;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.gxwebsoft.common.core.constants.ArticleConstants.CACHE_KEY_ARTICLE;

/**
 * 文章Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsArticleServiceImpl extends ServiceImpl<CmsArticleMapper, CmsArticle> implements CmsArticleService {
  @Resource
  private CmsNavigationService cmsNavigationService;
  @Resource
  private CmsArticleContentService cmsArticleContentService;
  @Resource
  private CmsArticleContentService articleContentService;
  @Resource
  private UserService userService;
  @Resource
  private CmsModelService cmsModelService;
  @Resource
  private RedisUtil redisUtil;

  private static final int PERMISSION_PASSWORD = 2;
  private static final long CACHE_MINUTES = 30L;

  @Override
  public PageResult<CmsArticle> pageRel(CmsArticleParam param) {
    if (param.getParentId() != null && !param.getParentId().equals(0)) {
      final List<CmsNavigation> cmsNavigations = cmsNavigationService.list(new LambdaQueryWrapper<CmsNavigation>().eq(CmsNavigation::getParentId, param.getParentId()));
      if (!CollectionUtils.isEmpty(cmsNavigations)) {
        param.setCategoryIds(cmsNavigations.stream().map(CmsNavigation::getNavigationId).collect(Collectors.toSet()));
      }
    }
    PageParam<CmsArticle, CmsArticleParam> page = new PageParam<>(param);
    page.setDefaultOrder("sort_number asc,create_time desc");
    List<CmsArticle> list = baseMapper.selectPageRel(page, param);

    return new PageResult<>(list, page.getTotal());
  }

  @Override
  public List<CmsArticle> listRel(CmsArticleParam param) {
    List<CmsArticle> list = baseMapper.selectListRel(param);
    // 排序
    PageParam<CmsArticle, CmsArticleParam> page = new PageParam<>();
    page.setDefaultOrder("sort_number asc,create_time desc");

    if (StrUtil.isNotBlank(param.getSceneType())) {
      // 导出数据
      if (param.getSceneType().equals("Content")) {
        final Set<Integer> collectIds = list.stream().map(CmsArticle::getArticleId).collect(Collectors.toSet());
        final List<CmsArticleContent> contents = cmsArticleContentService.list(new LambdaQueryWrapper<CmsArticleContent>().in(CmsArticleContent::getArticleId, collectIds));
        final Map<Integer, List<CmsArticleContent>> collect = contents.stream().collect(Collectors.groupingBy(CmsArticleContent::getArticleId));
        list.forEach(d -> {
          final List<CmsArticleContent> cmsArticleContents = collect.get(d.getArticleId());
          final CmsArticleContent content = cmsArticleContents.get(0);
          if (ObjectUtil.isNotEmpty(content)) {
            d.setContent(content.getContent());
          }
        });
      }
    }
    return page.sortRecords(list);
  }

  @Override
  public CmsArticle getByIdRel(Integer articleId) {
//    String key = CACHE_KEY_ARTICLE + articleId;
//    final String cacheInfo = redisUtil.get(key);
//    if (StrUtil.isNotBlank(cacheInfo)) {
//      final CmsArticle article = JSONUtil.parseObject(cacheInfo, CmsArticle.class);
//      // 更新阅读数量
//      assert article != null;
//      article.setActualViews(article.getActualViews() + 1);
//      updateById(article);
//      return article;
//    }
    // 缓存不存在
    CmsArticleParam param = new CmsArticleParam();
    param.setArticleId(articleId);
    final CmsArticle article = param.getOne(baseMapper.selectListRel(param));
    if (ObjectUtil.isNotEmpty(article)) {
      // 更新阅读数量
      article.setActualViews(article.getActualViews() + 1);
      updateById(article);
      // 读取Banner
//      final CmsModel model = cmsModelService.getOne(new LambdaQueryWrapper<CmsModel>().eq(CmsModel::getModel, article.getModel()).last("limit 1"));
//      if (ObjectUtil.isNotEmpty(model)) {
//        article.setBanner(model.getBanner());
//      }
      // 附加文字内容
      CmsArticleContent content = articleContentService.getOne(new LambdaQueryWrapper<CmsArticleContent>().eq(CmsArticleContent::getArticleId, article.getArticleId()).last("limit 1"));
      if (content != null) {
        article.setContent(content.getContent());
      }
//      redisUtil.set(key, article, CACHE_MINUTES, TimeUnit.MINUTES);
      return article;
    }
    return null;
  }

  @Override
  public void saveInc(Integer formId) {
    final CmsArticle article = getById(formId);
    if (ObjectUtil.isNull(article)) {
      return;
    }
    article.setBmUsers(article.getBmUsers() + 1);
    updateById(article);
  }

  @Override
  public boolean saveRel(CmsArticle article) {
    try {
      // 保存文章模型
      final CmsNavigation cmsNavigation = cmsNavigationService.getByIdRel(article.getCategoryId());
      final CmsModel modelInfo = cmsNavigation.getModelInfo();
      final String componentDetail = modelInfo.getComponentDetail();
      if (ObjectUtil.isNotEmpty(componentDetail)) {
        final String[] split = componentDetail.split("/");
        article.setModel(modelInfo.getModel());
        if (split[2].equals(modelInfo.getModel())) {
          article.setDetail(split[2].concat("/").concat(split[3]));
        } else {
          article.setDetail(split[2]);
        }
      }
      // 是否密码可见
      if (article.getPermission() != null && article.getPermission() == PERMISSION_PASSWORD) {
        article.setPassword(userService.encodePassword(article.getPassword()));
      }
      // 保存文章内容
      final boolean save = save(article);
      if(StrUtil.isBlank(article.getContent())){
        return true;
      }
      if (save) {
        final CmsArticleContent content = new CmsArticleContent();
        content.setArticleId(article.getArticleId());
        content.setContent(article.getContent());
        content.setTenantId(article.getTenantId());
        articleContentService.save(content);
        // 同步翻译并保存
        articleContentService.translate(article);
        return true;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  @Override
  public boolean updateByIdRel(CmsArticle article) {
    // 是否密码可见
    if (article.getPermission() == PERMISSION_PASSWORD) {
      article.setPassword(userService.encodePassword(article.getPassword()));
    }
    try {
      // 保存文章模型
      final CmsNavigation cmsNavigation = cmsNavigationService.getByIdRel(article.getCategoryId());
      // 模型信息
      if (ObjectUtil.isNotEmpty(cmsNavigation)) {
        final CmsModel modelInfo = cmsNavigation.getModelInfo();
        final String componentDetail = modelInfo.getComponentDetail();
        if (ObjectUtil.isNotEmpty(componentDetail)) {
          final String[] split = componentDetail.split("/");
          article.setModel(modelInfo.getModel());
          if (split[2].equals(modelInfo.getModel())) {
            article.setDetail(split[2].concat("/").concat(split[3]));
          } else {
            article.setDetail(split[2]);
          }
        }
      }
      // 修正父级栏目ID
      if (article.getParentId().equals(0)) {
        final CmsNavigation current = cmsNavigationService.getById(article.getCategoryId());
        if (ObjectUtil.isNotEmpty(current)) {
          article.setParentId(current.getParentId());
        }
      }
      if (updateById(article)) {
        if (StrUtil.isBlank(article.getContent())) {
          return true;
        }
        // 删除缓存
        String key = CACHE_KEY_ARTICLE + article.getArticleId();
        redisUtil.delete(key);
        // 更新内容
        final boolean update = articleContentService.update(new LambdaUpdateWrapper<CmsArticleContent>().eq(CmsArticleContent::getArticleId, article.getArticleId()).set(CmsArticleContent::getContent, article.getContent()));
        if (update) {
          // 同步翻译并保存
          article.setIsUpdate(true);
          articleContentService.translate(article);
          return true;
        } else {
          // 添加内容
          final CmsArticleContent content = new CmsArticleContent();
          content.setArticleId(article.getArticleId());
          content.setContent(article.getContent());
          content.setTenantId(article.getTenantId());
          articleContentService.save(content);
        }
        return true;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return false;
  }

}
