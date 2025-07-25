package com.gxwebsoft.cms.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.cms.entity.*;
import com.gxwebsoft.cms.param.CmsNavigationParam;
import com.gxwebsoft.cms.service.CmsNavigationService;
import com.gxwebsoft.cms.service.CmsWebsiteFieldService;
import com.gxwebsoft.cms.service.CmsWebsiteSettingService;
import com.gxwebsoft.common.core.utils.CommonUtil;
import com.gxwebsoft.common.core.utils.JSONUtil;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsWebsiteService;
import com.gxwebsoft.cms.param.CmsWebsiteParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.system.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

import java.util.concurrent.TimeUnit;

/**
 * 网站信息记录表控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
@Slf4j
@Api(tags = "网站信息记录表管理")
@RestController
@RequestMapping("/api/cms/cms-website")
public class CmsWebsiteController extends BaseController {
  @Resource
  private CmsWebsiteService cmsWebsiteService;
  @Resource
  private RedisUtil redisUtil;
  @Resource
  private CmsWebsiteFieldService cmsWebsiteFieldService;
  @Resource
  private CmsNavigationService cmsNavigationService;
  @Resource
  private CmsWebsiteSettingService cmsWebsiteSettingService;

  private static final String SITE_INFO_KEY_PREFIX = "SiteInfo:";
  private static final String MP_INFO_KEY_PREFIX = "MpInfo:";
  private static final String SELECT_PAYMENT_KEY_PREFIX = "SelectPayment:";
  private static final String SYS_DOMAIN_SUFFIX = ".websoft.top";
  private static final String DOMAIN_SUFFIX = ".wsdns.cn";

  @ApiOperation("分页查询网站信息记录表")
  @GetMapping("/page")
  public ApiResult<PageResult<CmsWebsite>> page(CmsWebsiteParam param) {
    // 使用关联查询
    return success(cmsWebsiteService.pageRel(param));
  }

  @ApiOperation("查询全部网站信息记录表")
  @GetMapping()
  public ApiResult<List<CmsWebsite>> list(CmsWebsiteParam param) {
    // 使用关联查询
    return success(cmsWebsiteService.listRel(param));
  }

  @ApiOperation("分页查询网站信息记录表")
  @GetMapping("/pageAll")
  public ApiResult<PageResult<CmsWebsite>> pageAll(CmsWebsiteParam param) {
    return success(cmsWebsiteService.pageRelAll(param));
  }

  @ApiOperation("根据id查询网站信息记录表")
  @GetMapping("/{id}")
  public ApiResult<CmsWebsite> get(@PathVariable("id") Integer id) {
    // 使用关联查询
    return success(cmsWebsiteService.getByIdRel(id));
  }

  @ApiOperation("根据id查询网站信息记录表")
  @GetMapping("/getAll/{id}")
  public ApiResult<CmsWebsite> getAll(@PathVariable("id") Integer id) {
    // 使用关联查询
    return success(cmsWebsiteService.getByIdRelAll(id));
  }

  @PreAuthorize("hasAuthority('cms:website:save')")
  @ApiOperation("添加网站信息记录表")
  @PostMapping()
  public ApiResult<?> save(@RequestBody CmsWebsite cmsWebsite) {
    // 记录当前登录用户id
    User loginUser = getLoginUser();
    if (loginUser != null) {
      cmsWebsite.setLoginUser(loginUser);
      return success("创建成功", cmsWebsiteService.create(cmsWebsite));
    }
    return fail("创建失败");
  }

  @PreAuthorize("hasAuthority('cms:website:update')")
  @ApiOperation("修改网站信息记录表")
  @PutMapping()
  public ApiResult<?> update(@RequestBody CmsWebsite cmsWebsite) {
    if (cmsWebsiteService.updateById(cmsWebsite)) {
      return success("修改成功");
    }
    return fail("修改失败");
  }

  @PreAuthorize("hasAuthority('cms:website:update')")
  @ApiOperation("修改网站信息记录表")
  @PutMapping("/updateAll")
  public ApiResult<?> updateAll(@RequestBody CmsWebsite cmsWebsite) {
    if (cmsWebsiteService.updateByIdAll(cmsWebsite)) {
      return success("修改成功");
    }
    return fail("修改失败");
  }

  @PreAuthorize("hasAuthority('cms:website:remove')")
  @ApiOperation("删除网站信息记录表")
  @DeleteMapping("/{id}")
  public ApiResult<?> remove(@PathVariable("id") Integer id) {
    if (cmsWebsiteService.removeById(id)) {
      return success("删除成功");
    }
    return fail("删除失败");
  }

  @PreAuthorize("hasAuthority('cms:website:remove')")
  @ApiOperation("删除网站信息记录表")
  @DeleteMapping("/removeAll/{id}")
  public ApiResult<?> removeAll(@PathVariable("id") Integer id) {
    if (cmsWebsiteService.removeByIdAll(id)) {
      return success("删除成功");
    }
    return fail("删除失败");
  }

  @PreAuthorize("hasAuthority('cms:website:save')")
  @ApiOperation("批量添加网站信息记录表")
  @PostMapping("/batch")
  public ApiResult<?> saveBatch(@RequestBody List<CmsWebsite> list) {
    if (cmsWebsiteService.saveBatch(list)) {
      return success("添加成功");
    }
    return fail("添加失败");
  }

  @PreAuthorize("hasAuthority('cms:website:update')")
  @ApiOperation("批量修改网站信息记录表")
  @PutMapping("/batch")
  public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsWebsite> batchParam) {
    if (batchParam.update(cmsWebsiteService, "website_id")) {
      return success("修改成功");
    }
    return fail("修改失败");
  }

  @PreAuthorize("hasAuthority('cms:website:remove')")
  @ApiOperation("批量删除网站信息记录表")
  @DeleteMapping("/batch")
  public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
    if (cmsWebsiteService.removeByIds(ids)) {
      return success("删除成功");
    }
    return fail("删除失败");
  }

  @ApiOperation("网站基本信息")
  @GetMapping("/getSiteInfo")
  public ApiResult<CmsWebsite> getSiteInfo() {
    if (ObjectUtil.isEmpty(getTenantId())) {
      return fail("参数不正确", null);
    }

    String key = SITE_INFO_KEY_PREFIX + getTenantId();
    final String siteInfo = redisUtil.get(key);
    if (StrUtil.isNotBlank(siteInfo)) {
      log.info("从缓存获取网站信息： = {}", key);
//      return success(JSONUtil.parseObject(siteInfo, CmsWebsite.class));
    }

    // 获取站点信息
    CmsWebsite website = cmsWebsiteService.getOne(new LambdaQueryWrapper<CmsWebsite>().eq(CmsWebsite::getDeleted, 0).last("limit 1"));

    // 创建默认站点
    if (ObjectUtil.isEmpty(website)) {
      return success("请先创建站点...", null);
    }

    // 站点异常状态
    setWebsiteStatus(website);

    // 站点配置参数
    HashMap<String, Object> config = buildWebsiteConfig(website);
    website.setConfig(config);

    // 网站导航
    setWebsiteNavigation(website);

    // 网站设置信息
    setWebsiteSetting(website);

    // 服务器时间
    HashMap<String, Object> serverTime = buildServerTime();
    website.setServerTime(serverTime);

    // 即将过期(一周内过期的)
    website.setSoon(DateUtil.offsetDay(website.getExpirationTime(), -30).compareTo(DateUtil.date()));
    // 是否过期 -1已过期 大于0 未过期
    website.setExpired(website.getExpirationTime().compareTo(DateUtil.date()));
    // 剩余天数
    website.setExpiredDays(DateUtil.betweenDay(website.getExpirationTime(), DateUtil.date(), false));

    redisUtil.set(key, website, 1L, TimeUnit.DAYS);
    return success(website);
  }

  private void setWebsiteStatus(CmsWebsite website) {
    if (!website.getRunning().equals(1)) {
      // 未开通
      if (website.getRunning().equals(0)) {
        website.setStatusIcon("error");
        website.setStatusText("该站点未开通");
      }
      // 维护中
      if (website.getRunning().equals(2)) {
        website.setStatusIcon("warning");
      }
      // 已关闭
      if (website.getRunning().equals(3)) {
        website.setStatusIcon("error");
        website.setStatusText("已关闭");
      }
      // 已欠费停机
      if (website.getRunning().equals(4)) {
        website.setStatusIcon("error");
        website.setStatusText("已欠费停机");
      }
      // 违规关停
      if (website.getRunning().equals(5)) {
        website.setStatusIcon("error");
        website.setStatusText("违规关停");
      }
    }
  }

  private HashMap<String, Object> buildWebsiteConfig(CmsWebsite website) {
    HashMap<String, Object> config = new HashMap<>();
    LambdaQueryWrapper<CmsWebsiteField> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(CmsWebsiteField::getDeleted, 0);
    final List<CmsWebsiteField> fields = cmsWebsiteFieldService.list(wrapper);
    fields.forEach(d -> {
      config.put(d.getName(), d.getValue());
    });
    config.put("SysDomain", getSysDomain(website));
    config.put("Domain", getDomain(website));
    return config;
  }

  private String getSysDomain(CmsWebsite website) {
    return StrUtil.isNotBlank(website.getWebsiteCode()) ? website.getWebsiteCode() + SYS_DOMAIN_SUFFIX : website.getTenantId() + SYS_DOMAIN_SUFFIX;
  }

  private String getDomain(CmsWebsite website) {
    return StrUtil.isNotBlank(website.getDomain()) ? website.getDomain() : website.getWebsiteCode() + DOMAIN_SUFFIX;
  }

  private void setWebsiteNavigation(CmsWebsite website) {
    final CmsNavigationParam navigationParam = new CmsNavigationParam();
    navigationParam.setHide(0);
    navigationParam.setTop(0);
    navigationParam.setBottom(null);
    final List<CmsNavigation> topNavs = cmsNavigationService.listRel(navigationParam);
    // 顶部菜单
    website.setTopNavs(CommonUtil.toTreeData(topNavs, 0, CmsNavigation::getParentId, CmsNavigation::getNavigationId, CmsNavigation::setChildren));
    navigationParam.setTop(null);
    navigationParam.setBottom(0);
    final List<CmsNavigation> bottomNavs = cmsNavigationService.listRel(navigationParam);
    // 底部菜单
    website.setBottomNavs(CommonUtil.toTreeData(bottomNavs, 0, CmsNavigation::getParentId, CmsNavigation::getNavigationId, CmsNavigation::setChildren));
  }

  private void setWebsiteSetting(CmsWebsite website) {
    final CmsWebsiteSetting setting = cmsWebsiteSettingService.getOne(new LambdaQueryWrapper<CmsWebsiteSetting>().eq(CmsWebsiteSetting::getWebsiteId, website.getWebsiteId()));
    if (ObjectUtil.isNotEmpty(setting)) {
      website.setSetting(setting);
    }
  }

  private HashMap<String, Object> buildServerTime() {
    HashMap<String, Object> serverTime = new HashMap<>();
    // 今天日期
    DateTime date = DateUtil.date();
    String today = DateUtil.today();
    // 明天日期
    final DateTime dateTime = DateUtil.tomorrow();
    String tomorrow = DateUtil.format(dateTime, "yyyy-MM-dd");
    // 后天日期
    final DateTime dateTime2 = DateUtil.offsetDay(date, 2);
    final String afterDay = DateUtil.format(dateTime2, "yyyy-MM-dd");
    // 今天星期几
    final int week = DateUtil.thisDayOfWeek();
    final DateTime nextWeek = DateUtil.nextWeek();
    serverTime.put("now", DateUtil.now());
    serverTime.put("today", today);
    serverTime.put("tomorrow", tomorrow);
    serverTime.put("afterDay", afterDay);
    serverTime.put("week", week);
    serverTime.put("nextWeek", nextWeek);
    return serverTime;
  }

  @ApiOperation("清除缓存")
  @DeleteMapping("/clearSiteInfo/{key}")
  public ApiResult<?> clearSiteInfo(@PathVariable("key") String key) {
    // 清除指定key
    redisUtil.delete(key);
    // 清除缓存
    redisUtil.delete(SITE_INFO_KEY_PREFIX.concat(getTenantId().toString()));
    // 清除小程序缓存
    redisUtil.delete(MP_INFO_KEY_PREFIX.concat(getTenantId().toString()));
    // 选择支付方式
    redisUtil.delete(SELECT_PAYMENT_KEY_PREFIX.concat(getTenantId().toString()));
    return success("清除成功");
  }

}
