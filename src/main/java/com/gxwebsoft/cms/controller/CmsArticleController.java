package com.gxwebsoft.cms.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gxwebsoft.cms.entity.*;
import com.gxwebsoft.cms.param.CmsArticleImportParam;
import com.gxwebsoft.cms.service.*;
import com.gxwebsoft.common.core.utils.JSONUtil;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.param.CmsArticleParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

import static com.gxwebsoft.common.core.constants.ArticleConstants.CACHE_KEY_ARTICLE;

/**
 * 文章控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Slf4j
@Validated
@Api(tags = "文章管理")
@RestController
@RequestMapping("/api/cms/cms-article")
public class CmsArticleController extends BaseController {
  @Resource
  private CmsArticleService cmsArticleService;
  @Resource
  private CmsArticleContentService articleContentService;
  @Resource
  private CmsNavigationService cmsNavigationService;
  @Resource
  private CmsModelService cmsModelService;
  @Resource
  private UserService userService;
  @Resource
  private RedisUtil redisUtil;

  private static final long CACHE_MINUTES = 30L;

  @ApiOperation("分页查询文章")
  @GetMapping("/page")
  public ApiResult<PageResult<CmsArticle>> page(CmsArticleParam param) {
    // 使用关联查询
    return success(cmsArticleService.pageRel(param));
  }

  @ApiOperation("查询全部文章")
  @GetMapping()
  public ApiResult<List<CmsArticle>> list(CmsArticleParam param) {
    // 使用关联查询
    return success(cmsArticleService.listRel(param));
  }

  @ApiOperation("根据id查询文章")
  @GetMapping("/{id}")
  public ApiResult<CmsArticle> get(@PathVariable("id") @NotNull Integer id) {
    final CmsArticle article = cmsArticleService.getByIdRel(id);
    if (ObjectUtil.isNotEmpty(article)) {
      return success(article);
    }
    return fail("文章ID不存在",null);
  }

  @PreAuthorize("hasAuthority('cms:cmsArticle:save')")
  @ApiOperation("添加文章")
  @PostMapping()
  public ApiResult<?> save(@RequestBody @Valid CmsArticle article) {
    // 记录当前登录用户id
    User loginUser = getLoginUser();
    if (loginUser != null) {
      article.setUserId(loginUser.getUserId());
      article.setAuthor(loginUser.getNickname());
      article.setMerchantId(loginUser.getMerchantId());
      if (cmsArticleService.saveRel(article)) {
        return success("添加成功");
      }
    }
    return fail("添加失败");
  }

  @PreAuthorize("hasAuthority('cms:cmsArticle:update')")
  @ApiOperation("修改文章")
  @PutMapping()
  public ApiResult<?> update(@RequestBody CmsArticle article) {
    if (cmsArticleService.updateByIdRel(article)) {
      return success("修改成功");
    }
    return fail("修改失败");
  }

  @PreAuthorize("hasAuthority('cms:cmsArticle:remove')")
  @ApiOperation("删除文章")
  @DeleteMapping("/{id}")
  public ApiResult<?> remove(@PathVariable("id") Integer id) {
    if (cmsArticleService.removeById(id)) {
      redisUtil.delete(CACHE_KEY_ARTICLE + id);
      return success("删除成功");
    }
    return fail("删除失败");
  }

  @PreAuthorize("hasAuthority('cms:cmsArticle:save')")
  @ApiOperation("批量添加文章")
  @PostMapping("/batch")
  public ApiResult<?> saveBatch(@RequestBody List<CmsArticle> list) {
    if (cmsArticleService.saveBatch(list)) {
      return success("添加成功");
    }
    return fail("添加失败");
  }

  @PreAuthorize("hasAuthority('cms:cmsArticle:update')")
  @ApiOperation("批量修改文章")
  @PutMapping("/batch")
  public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsArticle> batchParam) {
    if (batchParam.update(cmsArticleService, "article_id")) {
      // 删除缓存
      final List<Serializable> ids = batchParam.getIds();
      ids.forEach(id -> {
        redisUtil.delete(CACHE_KEY_ARTICLE + id);
      });
      return success("修改成功");
    }
    return fail("修改失败");
  }

  @PreAuthorize("hasAuthority('cms:cmsArticle:remove')")
  @ApiOperation("批量删除文章")
  @DeleteMapping("/batch")
  public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
    if (cmsArticleService.removeByIds(ids)) {
      // 删除缓存
      ids.forEach(id -> {
        redisUtil.delete(CACHE_KEY_ARTICLE + id);
      });
      return success("删除成功");
    }
    return fail("删除失败");
  }

  @ApiOperation("读取上一篇")
  @GetMapping("/getPrevious/{id}")
  public ApiResult<CmsArticle> getPrevious(@PathVariable("id") Integer id) {
    final CmsArticle item = cmsArticleService.getById(id);
    if (ObjectUtil.isEmpty(item)) {
      return success("没有找到上一篇文章",null);
    }
    CmsArticle article;
    // TODO 按排序号规则
    LambdaQueryWrapper<CmsArticle> wrapper = new LambdaQueryWrapper<>();
    wrapper.lt(CmsArticle::getSortNumber, item.getSortNumber());
    wrapper.eq(CmsArticle::getStatus, 0);
    wrapper.eq(CmsArticle::getType, 0);
    wrapper.eq(CmsArticle::getCategoryId, item.getCategoryId());
    wrapper.orderByDesc(CmsArticle::getSortNumber);
    wrapper.last("limit 1");
    article = cmsArticleService.getOne(wrapper);
    if (ObjectUtil.isNotEmpty(article)) {
      return success(article);
    }
    // TODO 按ID排序
    LambdaQueryWrapper<CmsArticle> wrapper2 = new LambdaQueryWrapper<>();
    wrapper2.lt(CmsArticle::getArticleId, item.getArticleId());
    wrapper2.eq(CmsArticle::getStatus, 0);
    wrapper2.eq(CmsArticle::getCategoryId, item.getCategoryId());
    wrapper2.last("limit 1");
    wrapper2.orderByDesc(CmsArticle::getArticleId);
    article = cmsArticleService.getOne(wrapper2);
    return success(article);
  }

  @ApiOperation("读取下一篇")
  @GetMapping("/getNext/{id}")
  public ApiResult<CmsArticle> getNext(@PathVariable("id") Integer id) {
    CmsArticle item = cmsArticleService.getById(id);
    if (ObjectUtil.isEmpty(item)) {
      return success("没有找到下一篇文章",null);
    }
    CmsArticle article;
    // TODO 按排序号规则
    LambdaQueryWrapper<CmsArticle> wrapper = new LambdaQueryWrapper<>();
    wrapper.gt(CmsArticle::getSortNumber, item.getSortNumber());
    wrapper.eq(CmsArticle::getStatus, 0);
    wrapper.eq(CmsArticle::getType, 0);
    wrapper.eq(CmsArticle::getCategoryId, item.getCategoryId());
    wrapper.orderByAsc(CmsArticle::getSortNumber);
    wrapper.last("limit 1");
    article = cmsArticleService.getOne(wrapper);
    if (ObjectUtil.isNotEmpty(article)) {
      return success(article);
    }
    // TODO 按ID排序
    LambdaQueryWrapper<CmsArticle> wrapper2 = new LambdaQueryWrapper<>();
    wrapper2.gt(CmsArticle::getArticleId, item.getArticleId());
    wrapper2.eq(CmsArticle::getStatus, 0);
    wrapper2.eq(CmsArticle::getCategoryId, item.getCategoryId());
    wrapper2.last("limit 1");
    wrapper2.orderByAsc(CmsArticle::getArticleId);
    article = cmsArticleService.getOne(wrapper2);
    return success(article);
  }

  @ApiOperation("统计信息")
  @GetMapping("/data")
  public ApiResult<Map<String, Integer>> data(CmsArticleParam param) {
    Map<String, Integer> data = new HashMap<>();
    final LambdaQueryWrapper<CmsArticle> wrapper = new LambdaQueryWrapper<>();

    if (param.getMerchantId() != null) {
      wrapper.eq(CmsArticle::getMerchantId, param.getMerchantId());
    }

    Integer totalNum = cmsArticleService.count(
      wrapper.eq(CmsArticle::getDeleted, 0).eq(CmsArticle::getStatus, 0)
    );
    data.put("totalNum", totalNum);

    Integer totalNum2 = cmsArticleService.count(
      wrapper.eq(CmsArticle::getStatus, 1)
    );
    data.put("totalNum2", totalNum2);

    Integer totalNum3 = cmsArticleService.count(
      wrapper.gt(CmsArticle::getStatus, 1)
    );
    data.put("totalNum3", totalNum3);

    return success(data);
  }

  @ApiOperation("密码校验")
  @GetMapping("/checkArticlePassword")
  public ApiResult<?> checkArticlePassword(CmsArticle param) {
    if (!userService.comparePassword(param.getPassword(), param.getPassword2())) {
      return fail("密码不正确");
    }
    return success("密码正确");
  }

  /**
   * excel批量导入文章
   */
  @PreAuthorize("hasAuthority('cms:cmsArticle:save')")
  @ApiOperation("批量导入文章")
  @Transactional(rollbackFor = {Exception.class})
  @PostMapping("/import")
  public ApiResult<List<String>> importBatch(MultipartFile file) {
    ImportParams importParams = new ImportParams();
    try {
      List<CmsArticleImportParam> list = ExcelImportUtil.importExcel(file.getInputStream(), CmsArticleImportParam.class, importParams);
      list.forEach(d -> {
        CmsArticle item = JSONUtil.parseObject(JSONUtil.toJSONString(d), CmsArticle.class);
        assert item != null;
        if (ObjectUtil.isNotEmpty(item)) {
          if (item.getStatus() == null) {
            item.setStatus(1);
          }
          if (cmsArticleService.save(item)) {
            CmsArticleContent content = new CmsArticleContent();
            content.setArticleId(item.getArticleId());
            content.setContent(item.getContent());
            articleContentService.save(content);
          }
        }
      });
      return success("成功导入" + list.size() + "条", null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return fail("导入失败", null);
  }

  @ApiOperation("按标签分页查询")
  @GetMapping("/findTags")
  public ApiResult<List<CmsArticle>> findTags(CmsArticleParam param) {
    final String tags = param.getTags();
    if (StringUtils.isNotBlank(tags)) {
      final String[] split = tags.split(",");
      final List<String> list = Arrays.asList(split);
      LambdaQueryWrapper<CmsArticle> queryWrapper = new LambdaQueryWrapper();
      if (StrUtil.isNotBlank(tags)) {
        for (String s : list) {
          queryWrapper.or().like(CmsArticle::getTags, s);
//        queryWrapper.or().apply("LOCATE(" + "'" + s + "'," + "tags" + ") > 0");
        }
      }
      if (param.getCategoryId() != null) {
        queryWrapper.eq(CmsArticle::getCategoryId, param.getCategoryId());
      }
      if (param.getDetail() != null) {
        queryWrapper.eq(CmsArticle::getDetail, param.getDetail());
      }
      queryWrapper.last("limit 8");
      List<CmsArticle> articles = cmsArticleService.list(queryWrapper);
      return success(articles);
    }
    return success("", null);
  }

  @ApiOperation("按标签分页查询")
  @GetMapping("/pageTags")
  public ApiResult<List<CmsArticle>> pageTags(CmsArticleParam param) {
    final String tags = param.getTags();
    if (StringUtils.isNotBlank(tags)) {
      final String[] split = tags.split(",");
      final List<String> list = Arrays.asList(split);
      LambdaQueryWrapper<CmsArticle> queryWrapper = new LambdaQueryWrapper<>();
      for (String s : list) {
        queryWrapper.or().like(CmsArticle::getTags, s);
      }
      queryWrapper.orderByDesc(CmsArticle::getCreateTime);
      queryWrapper.last("limit 100");
      List<CmsArticle> articles = cmsArticleService.list(queryWrapper);
      if (!articles.isEmpty()) {
        List<CmsNavigation> navigationList = cmsNavigationService.listByIds(articles.stream().map(CmsArticle::getCategoryId).toList());
        for (CmsArticle article : articles) {
          for (CmsNavigation navigation : navigationList) {
            if (article.getCategoryId().equals(navigation.getNavigationId())) {
              article.setCategoryName(navigation.getTitle());
            }
          }
        }
      }
      return success(articles);
    }
    return success("", null);
  }

  @ApiOperation("按IDS查询")
  @GetMapping("/getByIds")
  public ApiResult<List<CmsArticle>> getByIds(CmsArticleParam param) {
    // 使用关联查询
    return success(cmsArticleService.list(new LambdaQueryWrapper<CmsArticle>().in(CmsArticle::getArticleId, param.getArticleIds())));
  }
}
