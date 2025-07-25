package com.gxwebsoft.cms.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gxwebsoft.cms.entity.CmsMpField;
import com.gxwebsoft.cms.entity.CmsMpMenu;
import com.gxwebsoft.cms.entity.CmsMpPages;
import com.gxwebsoft.cms.service.CmsMpFieldService;
import com.gxwebsoft.cms.service.CmsMpMenuService;
import com.gxwebsoft.cms.service.CmsMpPagesService;
import com.gxwebsoft.common.core.utils.JSONUtil;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsMpService;
import com.gxwebsoft.cms.entity.CmsMp;
import com.gxwebsoft.cms.param.CmsMpParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.system.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 小程序信息控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "小程序信息管理")
@RestController
@RequestMapping("/api/cms/cms-mp")
public class CmsMpController extends BaseController {
    @Resource
    private CmsMpService cmsMpService;
    @Resource
    private CmsMpPagesService cmsMpPagesService;
    @Resource
    private CmsMpFieldService mpFieldService;
    @Resource
    private CmsMpMenuService cmsMpMenuService;
    @Resource
    private RedisUtil redisUtil;

    @ApiOperation("分页查询小程序信息")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsMp>> page(CmsMpParam param) {
        // 使用关联查询
        return success(cmsMpService.pageRel(param));
    }

    @ApiOperation("查询全部小程序信息")
    @GetMapping()
    public ApiResult<List<CmsMp>> list(CmsMpParam param) {
        // 使用关联查询
        return success(cmsMpService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsMp:list')")
    @OperationLog
    @ApiOperation("根据id查询小程序信息")
    @GetMapping("/{id}")
    public ApiResult<CmsMp> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsMpService.getByIdRel(id));
    }

    @ApiOperation("添加小程序信息")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsMp cmsMp) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsMp.setUserId(loginUser.getUserId());
        }
        if (cmsMpService.save(cmsMp)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改小程序信息")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsMp cmsMp) {
        if (cmsMpService.updateById(cmsMp)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除小程序信息")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsMpService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加小程序信息")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsMp> list) {
        if (cmsMpService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改小程序信息")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsMp> batchParam) {
        if (batchParam.update(cmsMpService, "mp_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除小程序信息")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsMpService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("小程序基本信息")
    @GetMapping("/getMpInfo")
    public ApiResult<?> getMpInfo() {
      final Integer tenantId = getTenantId();
      String key = "MpInfo:" + tenantId;
      System.out.println("key = " + key);
      final String mpInfo = redisUtil.get(key);

      if (tenantId.equals(0)) {
        return fail("租户ID不存在", null);
      }
      System.out.println("mpInfo = " + mpInfo);
      // 从缓存读取信息
      if (StrUtil.isNotBlank(mpInfo)) {
        final Object object = JSONUtil.parseObject(mpInfo, Object.class);
        System.out.println("object = " + object);
        return success(object);
      }

      // 获取小程序
      if (cmsMpService.count(new LambdaUpdateWrapper<CmsMp>().eq(CmsMp::getDeleted, 0)) == 0) {
        // 创建小程序
        createMp();
      }

      HashMap<String, Object> map = new HashMap<>();

      // 获取小程序
      final CmsMp mp = cmsMpService.getOne(new LambdaQueryWrapper<CmsMp>().eq(CmsMp::getTenantId, tenantId).last("limit 1"));
      mp.setAppSecret(null);
      map.put("mp", mp);

      // 原生导航条
      final List<CmsMpPages> tabBar = cmsMpPagesService.list(new LambdaQueryWrapper<CmsMpPages>().eq(CmsMpPages::getSubpackage, "MainPackage").last("limit 5"));
      map.put("tabBar", tabBar);

      // 配置信息
      HashMap<String, Object> config = new HashMap<>();
      config.put("LICENSE_CODE", "");
      config.put("MAP_KEY", "");
      final List<CmsMpField> fields = mpFieldService.list();
      fields.forEach(d -> {
        config.put(d.getName(), d.getValue());
      });
      map.put("config", config);

      // 服务器时间
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
      serverTime.put("now", DateUtil.now());   // 2024-07-18 22:06:36
      serverTime.put("today", today);          // 2024-07-18
      serverTime.put("tomorrow", tomorrow);    // 2024-07-19
      serverTime.put("afterDay", afterDay);    // 2024-07-20
      serverTime.put("nextWeek", nextWeek);    // 2024-07-25 22:06:36
      serverTime.put("week", week);            // 5
      map.put("serverTime", serverTime);
      redisUtil.set(key, map, 1L, TimeUnit.DAYS);
      return success(map);
    }

  private void createMp() {
    System.out.println("创建小程序 = ");
    final User loginUser = getLoginUser();
    final Integer tenantId = getTenantId();
    // 创建网站记录
    final CmsMp mp = new CmsMp();
    mp.setTenantId(tenantId);
    mp.setAppId("小程序ID");
    mp.setMpName("小程序名称");
    mp.setMainPath("/pages/index");
    if (loginUser != null) {
      mp.setUserId(getLoginUserId());
    }
    mp.setExpirationTime(DateUtil.offset(DateUtil.date(), DateField.YEAR, 1));
    cmsMpService.save(mp);

    // 创建底部导航栏
    final CmsMpPages mpPages = new CmsMpPages();
    mpPages.setHome(1);
    mpPages.setTitle("首页");
    mpPages.setPath("/pages/index");
    mpPages.setSubpackage("MainPackage");
    mpPages.setIcon("HomeOutlined");
    mpPages.setSortNumber(0);
    mpPages.setTenantId(tenantId);
    cmsMpPagesService.save(mpPages);
    mpPages.setHome(0);
    mpPages.setTitle("分类");
    mpPages.setPath("/pages/category");
    mpPages.setSubpackage("MainPackage");
    mpPages.setIcon("AppstoreOutlined");
    mpPages.setSortNumber(0);
    cmsMpPagesService.save(mpPages);
    mpPages.setTitle("购物车");
    mpPages.setPath("/pages/category");
    mpPages.setSubpackage("MainPackage");
    mpPages.setIcon("ShoppingCartOutlined");
    mpPages.setSortNumber(0);
    cmsMpPagesService.save(mpPages);
    mpPages.setTitle("我的");
    mpPages.setPath("/pages/user");
    mpPages.setSubpackage("MainPackage");
    mpPages.setIcon("UserOutlined");
    mpPages.setSortNumber(0);
    cmsMpPagesService.save(mpPages);

    // 创建导航图标
    final CmsMpMenu mpMenu = new CmsMpMenu();
    mpMenu.setTenantId(tenantId);
    mpMenu.setTitle("分类1");
    mpMenu.setIcon("PictureOutlined");
    mpMenu.setPath("/package/order");
    mpMenu.setTarget("uni.navigateTo");
    cmsMpMenuService.save(mpMenu);
    mpMenu.setTitle("分类2");
    mpMenu.setIcon("PictureOutlined");
    mpMenu.setPath("/package/order");
    cmsMpMenuService.save(mpMenu);
    mpMenu.setTitle("分类3");
    mpMenu.setIcon("PictureOutlined");
    mpMenu.setPath("/package/order");
    cmsMpMenuService.save(mpMenu);
    mpMenu.setTitle("分类4");
    mpMenu.setIcon("PictureOutlined");
    mpMenu.setPath("/package/order");
    cmsMpMenuService.save(mpMenu);

    // 小程序配置信息
    CmsMpField field = new CmsMpField();
    field.setName("mpLogo");
    mpFieldService.save(field);
  }

}
