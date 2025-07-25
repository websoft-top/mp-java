package com.gxwebsoft.common.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.web.*;
import com.gxwebsoft.common.system.entity.Setting;
import com.gxwebsoft.common.system.entity.Tenant;
import com.gxwebsoft.common.system.param.SettingParam;
import com.gxwebsoft.common.system.service.SettingService;
import com.gxwebsoft.common.system.service.TenantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统设置控制器
 *
 * @author WebSoft
 * @since 2022-11-19 13:54:27
 */
@Api(tags = "系统设置管理")
@RestController
@RequestMapping("/api/system/setting")
public class SettingController extends BaseController {
    @Resource
    private SettingService settingService;
    @Resource
    private TenantService tenantService;
    @Resource
    private RedisUtil redisUtil;

    @PreAuthorize("hasAuthority('sys:setting:save')")
    @ApiOperation("分页查询系统设置")
    @GetMapping("/page")
    public ApiResult<PageResult<Setting>> page(SettingParam param) {
        PageParam<Setting, SettingParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(settingService.page(page, page.getWrapper()));
        // 使用关联查询
        //return success(settingService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:setting:save')")
    @ApiOperation("查询全部系统设置")
    @GetMapping()
    public ApiResult<List<Setting>> list(SettingParam param) {
        PageParam<Setting, SettingParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(settingService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(settingService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:setting:save')")
    @ApiOperation("根据id查询系统设置")
    @GetMapping("/{id}")
    public ApiResult<Setting> get(@PathVariable("id") Integer id) {
        return success(settingService.getById(id));
        // 使用关联查询
        //return success(settingService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:setting:save')")
    @ApiOperation("添加系统设置")
    @PostMapping()
    public ApiResult<?> save(@RequestBody Setting setting) {
        if (settingService.save(setting)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:setting:save')")
    @ApiOperation("修改系统设置")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Setting setting) {
        if (settingService.updateById(setting)) {
          // 更新系统设置信息到缓存
          String key = "setting:" + setting.getSettingKey() + ":" + getTenantId();
          System.out.println("key = " + key);
          redisUtil.set(key, JSON.parseObject(setting.getContent()));
          // 创建微信支付Bean
//          settingService.initConfig(setting);
          // 更新租户信息
          if (setting.getSettingKey().equals("setting")) {
            System.out.println("修改系统设置 = " + setting.getContent());
            final String content = setting.getContent();
            final JSONObject jsonObject = JSONObject.parseObject(content);
            final String siteName = jsonObject.getString("siteName");
            final String logo = jsonObject.getString("logo");
            System.out.println("siteName = " + siteName);
            final Tenant tenant = new Tenant();
            tenant.setTenantName(siteName);
            tenant.setTenantId(getTenantId());
            tenant.setLogo(logo);
            tenantService.updateById(tenant);
          }
          return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:setting:remove')")
    @ApiOperation("删除系统设置")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (settingService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:setting:save')")
    @ApiOperation("批量添加系统设置")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<Setting> list) {
        if (settingService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:setting:update')")
    @ApiOperation("批量修改系统设置")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<Setting> batchParam) {
        if (batchParam.update(settingService, "setting_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:setting:remove')")
    @ApiOperation("批量删除系统设置")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (settingService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:setting:data')")
    @ApiOperation("查询租户设置信息")
    @GetMapping("/data")
    public ApiResult<?> data() {
      return success(settingService.getData("setting"));
    }

    @PreAuthorize("hasAuthority('sys:setting:save')")
    @ApiOperation("更新主题皮肤")
    @PutMapping("/theme")
    public ApiResult<?> theme(@RequestBody Setting setting) {
      String key = "theme:".concat(getTenantId().toString());
      // 新增
      final Setting one = settingService.getOne(new LambdaQueryWrapper<Setting>().eq(Setting::getSettingKey, setting.getSettingKey()));
      if(one == null){
        settingService.save(setting);
        redisUtil.set(key,setting.getContent());
        return success("保存成功");
      }
      // 更新
      final Setting update = settingService.getOne(new LambdaQueryWrapper<Setting>().eq(Setting::getSettingKey, setting.getSettingKey()));
      update.setContent(setting.getContent());
      if (settingService.updateById(update)) {
        redisUtil.set(key,setting.getContent());
        return success("更新成功");
      }
      return fail("更新失败");
    }

    @ApiOperation("更新主题皮肤")
    @GetMapping("/getTheme")
    public ApiResult<?> getTheme() {
      String key = "theme:".concat(getTenantId().toString());
      return success("获取成功",redisUtil.get(key));
    }
}
