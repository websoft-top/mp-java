package com.gxwebsoft.cms.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gxwebsoft.cms.entity.CmsDesign;
import com.gxwebsoft.cms.entity.CmsModel;
import com.gxwebsoft.cms.service.CmsDesignService;
import com.gxwebsoft.cms.service.CmsModelService;
import com.gxwebsoft.common.core.utils.CommonUtil;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsNavigationService;
import com.gxwebsoft.cms.entity.CmsNavigation;
import com.gxwebsoft.cms.param.CmsNavigationParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;
import java.util.List;

/**
 * 网站导航记录表控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "网站导航记录表管理")
@RestController
@RequestMapping("/api/cms/cms-navigation")
public class CmsNavigationController extends BaseController {
  @Resource
  private CmsNavigationService cmsNavigationService;
  @Resource
  private CmsModelService cmsModelService;
  @Resource
  private CmsDesignService cmsDesignService;
  @Resource
  private UserService userService;
  @Resource
  private RedisUtil redisUtil;


  private static final String SITE_INFO_KEY_PREFIX = "SiteInfo:";

  @ApiOperation("分页查询网站导航记录表")
  @GetMapping("/page")
  public ApiResult<PageResult<CmsNavigation>> page(CmsNavigationParam param) {
    // 使用关联查询
    return success(cmsNavigationService.pageRel(param));
  }

  @ApiOperation("查询全部网站导航记录表")
  @GetMapping()
  public ApiResult<List<CmsNavigation>> list(CmsNavigationParam param) {
    // 使用关联查询
    return success(cmsNavigationService.listRel(param));
  }

  @ApiOperation("根据id查询网站导航记录表")
  @GetMapping("/{id}")
  public ApiResult<CmsNavigation> get(@PathVariable("id") Integer id) {
    // 使用关联查询
    return success(cmsNavigationService.getByIdRel(id));
  }

  @ApiOperation("添加网站导航记录表")
  @PostMapping()
  public ApiResult<?> save(@RequestBody CmsNavigation cmsNavigation) {
    // 记录当前登录用户id
    User loginUser = getLoginUser();
    if (loginUser != null) {
      cmsNavigation.setUserId(loginUser.getUserId());
      cmsNavigation.setTenantId(loginUser.getTenantId());
    }
    // 去除前面空格
    cmsNavigation.setTitle(StrUtil.trimStart(cmsNavigation.getTitle()));
    if (cmsNavigationService.save(cmsNavigation)) {
      // 添加成功事务处理
      cmsNavigationService.saveAsync(cmsNavigation);
      redisUtil.delete(SITE_INFO_KEY_PREFIX.concat(getTenantId().toString()));
      return success("添加成功");
    }
    return fail("添加失败");
  }

  @ApiOperation("修改网站导航记录表")
  @PutMapping()
  public ApiResult<?> update(@RequestBody CmsNavigation cmsNavigation) {
    if (cmsNavigationService.updateById(cmsNavigation)) {
      // 修改成功事务处理
      cmsNavigationService.saveAsync(cmsNavigation);
      redisUtil.delete(SITE_INFO_KEY_PREFIX.concat(getTenantId().toString()));
      return success("修改成功");
    }
    return fail("修改失败");
  }

  @ApiOperation("删除网站导航记录表")
  @DeleteMapping("/{id}")
  public ApiResult<?> remove(@PathVariable("id") Integer id) {
    if (cmsNavigationService.removeById(id)) {
      redisUtil.delete(SITE_INFO_KEY_PREFIX.concat(getTenantId().toString()));
      return success("删除成功");
    }
    return fail("删除失败");
  }

  @ApiOperation("批量添加网站导航记录表")
  @PostMapping("/batch")
  public ApiResult<?> saveBatch(@RequestBody List<CmsNavigation> list) {
    if (cmsNavigationService.saveBatch(list)) {
      redisUtil.delete(SITE_INFO_KEY_PREFIX.concat(getTenantId().toString()));
      return success("添加成功");
    }
    return fail("添加失败");
  }

  @ApiOperation("批量修改网站导航记录表")
  @PutMapping("/batch")
  public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsNavigation> batchParam) {
    if (batchParam.update(cmsNavigationService, "navigation_id")) {
      redisUtil.delete(SITE_INFO_KEY_PREFIX.concat(getTenantId().toString()));
      return success("修改成功");
    }
    return fail("修改失败");
  }

  @ApiOperation("批量删除网站导航记录表")
  @DeleteMapping("/batch")
  public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
    if (cmsNavigationService.removeByIds(ids)) {
      redisUtil.delete(SITE_INFO_KEY_PREFIX.concat(getTenantId().toString()));
      return success("删除成功");
    }
    return fail("删除失败");
  }

  @ApiOperation("获取树形结构的网站导航数据")
  @GetMapping("/tree")
  public ApiResult<List<CmsNavigation>> tree(CmsNavigationParam param) {
    param.setHide(0);
    final List<CmsNavigation> navigations = cmsNavigationService.listRel(param);
    return success(CommonUtil.toTreeData(navigations, 0, CmsNavigation::getParentId, CmsNavigation::getNavigationId, CmsNavigation::setChildren));
  }

  @ApiOperation("根据path获取导航")
  @GetMapping("/getNavigationByPath")
  public ApiResult<CmsNavigation> getNavigationByPath(CmsNavigationParam param) {
    final CmsNavigation navigation = cmsNavigationService.getOne(new LambdaUpdateWrapper<CmsNavigation>().eq(CmsNavigation::getModel, param.getModel()).last("limit 1"));
    if (ObjectUtil.isNotEmpty(navigation)) {
      // 页面元素
      final CmsDesign design = cmsDesignService.getOne(new LambdaUpdateWrapper<CmsDesign>().eq(CmsDesign::getCategoryId, navigation.getNavigationId()).last("limit 1"));
      // 模型信息
      final CmsModel model = cmsModelService.getOne(new LambdaQueryWrapper<CmsModel>().eq(CmsModel::getModel, navigation.getModel()).last("limit 1"));
      navigation.setBanner(model.getBanner());
      // 上级导航
      if (!navigation.getParentId().equals(0)) {
        final CmsNavigation parent = cmsNavigationService.getById(navigation.getParentId());
        navigation.setParentPath(parent.getPath());
        navigation.setParentName(parent.getTitle());
      }
      // 页面信息
      navigation.setDesign(design);
      // 页面布局
      if (ObjectUtil.isNotEmpty(design)) {
        navigation.setLayout(design.getLayout());
      }
    }
    return success(navigation);
  }

  @ApiOperation("密码校验")
  @GetMapping("/checkNavigationPassword")
  public ApiResult<?> checkNavigationPassword(CmsNavigationParam param) {
    if (!userService.comparePassword(param.getPassword(), param.getPassword2())) {
      return fail("密码不正确");
    }
    return success("密码正确");
  }

}
