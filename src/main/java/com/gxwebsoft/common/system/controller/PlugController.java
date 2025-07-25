package com.gxwebsoft.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Menu;
import com.gxwebsoft.common.system.entity.Plug;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.param.PlugParam;
import com.gxwebsoft.common.system.service.MenuService;
import com.gxwebsoft.common.system.service.PlugService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 插件扩展控制器
 *
 * @author 科技小王子
 * @since 2023-05-18 11:57:37
 */
@Api(tags = "插件扩展管理")
@RestController
@RequestMapping("/api/system/plug")
public class PlugController extends BaseController {
    @Resource
    private PlugService plugService;
    @Resource
    private MenuService menuService;

    @PreAuthorize("hasAuthority('sys:plug:list')")
    @ApiOperation("分页查询插件扩展")
    @GetMapping("/page")
    public ApiResult<PageResult<Plug>> page(PlugParam param) {
        // 如果不传userId，只显示审核通过的插件
        if (param.getUserId() == null) {
          param.setStatus(20);
        }
        // 使用关联查询
        return success(plugService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:plug:list')")
    @ApiOperation("查询全部插件扩展")
    @GetMapping()
    public ApiResult<List<Plug>> list(PlugParam param) {
        // 使用关联查询
        return success(plugService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:plug:list')")
    @ApiOperation("根据id查询插件扩展")
    @GetMapping("/{id}")
    public ApiResult<Plug> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(plugService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:plug:save')")
    @ApiOperation("添加插件扩展")
    @PostMapping()
    public ApiResult<?> save(@RequestBody Plug plug) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          plug.setUserId(loginUser.getUserId());
        }
        if (plugService.save(plug)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:plug:update')")
    @ApiOperation("修改插件扩展")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Plug plug) {
        if (plugService.updateById(plug)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:plug:remove')")
    @ApiOperation("删除插件扩展")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (plugService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:plug:save')")
    @ApiOperation("批量添加插件扩展")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<Plug> list) {
        if (plugService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:plug:update')")
    @ApiOperation("批量修改插件扩展")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<Plug> batchParam) {
        if (batchParam.update(plugService, "menu_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:plug:remove')")
    @ApiOperation("批量删除插件扩展")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (plugService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:plug:save')")
    @ApiOperation("发布插件")
    @PostMapping("/plug")
    public ApiResult<?> plug(@RequestBody Plug plug){
      final Integer menuId = plug.getParentId();
      // 查重
      final int count = plugService.count(new LambdaQueryWrapper<Plug>().eq(Plug::getMenuId, menuId));
      if(count > 0){
        return fail("请勿重复发布");
      }
      // 准备数据
      final Menu menu = menuService.getById(menuId);
      plug.setUserId(getLoginUserId());
      plug.setMenuId(menuId);
      plug.setTenantId(getTenantId());
      plug.setIcon(menu.getIcon());
      plug.setPath(menu.getPath());
      plug.setComponent(menu.getComponent());
      plug.setAuthority(menu.getAuthority());
      plug.setTitle(menu.getTitle());
      plug.setMenuType(menu.getMenuType());
      plug.setMeta(menu.getMeta());
      plug.setParentId(menu.getParentId());
      plug.setHide(menu.getHide());
      plug.setSortNumber(menu.getSortNumber());
      if(plugService.save(plug)){
        return success("发布成功");
      }
      return fail("发布失败");
    }
}
