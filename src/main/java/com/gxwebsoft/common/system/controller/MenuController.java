package com.gxwebsoft.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.web.*;
import com.gxwebsoft.common.system.entity.Menu;
import com.gxwebsoft.common.system.entity.Plug;
import com.gxwebsoft.common.system.param.MenuParam;
import com.gxwebsoft.common.system.service.MenuService;
import com.gxwebsoft.common.system.service.PlugService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单控制器
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:23
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/api/system/menu")
public class MenuController extends BaseController {
    @Resource
    private MenuService menuService;
    @Resource
    private PlugService plugService;

    @PreAuthorize("hasAuthority('sys:menu:list')")
    @ApiOperation("分页查询菜单")
    @GetMapping("/page")
    public ApiResult<PageResult<Menu>> page(MenuParam param) {
        PageParam<Menu, MenuParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(menuService.page(page, page.getWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:menu:list')")
    @ApiOperation("查询全部菜单")
    @GetMapping()
    public ApiResult<List<Menu>> list(MenuParam param) {
        PageParam<Menu, MenuParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(menuService.list(page.getOrderWrapper()));
    }

    @PreAuthorize("hasAuthority('sys:menu:list')")
    @ApiOperation("根据id查询菜单")
    @GetMapping("/{id}")
    public ApiResult<Menu> get(@PathVariable("id") Integer id) {
        return success(menuService.getById(id));
    }

    @PreAuthorize("hasAuthority('sys:menu:save')")
    @ApiOperation("添加菜单")
    @PostMapping()
    public ApiResult<?> add(@RequestBody Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0);
        }
        if (menuService.save(menu)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:update')")
    @ApiOperation("修改菜单")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Menu menu) {
        if (menuService.updateById(menu)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:remove')")
    @ApiOperation("删除菜单")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (menuService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:save')")
    @ApiOperation("批量添加菜单")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<Menu> menus) {
        if (menuService.saveBatch(menus)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:update')")
    @ApiOperation("批量修改菜单")
    @PutMapping("/batch")
    public ApiResult<?> updateBatch(@RequestBody BatchParam<Menu> batchParam) {
        if (batchParam.update(menuService, "menu_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:remove')")
    @ApiOperation("批量删除菜单")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (menuService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:update')")
    @ApiOperation("菜单克隆")
    @PostMapping("/clone")
    public ApiResult<?> onClone(@RequestBody MenuParam param){
      if(menuService.cloneMenu(param)){
        return success("克隆成功,请刷新");
      }
      return fail("克隆失败");
    }

    @PreAuthorize("hasAuthority('sys:menu:update')")
    @ApiOperation("安装插件")
    @GetMapping("/install/{id}")
    public ApiResult<?> install(@PathVariable("id") Integer id){
      if(menuService.install(id)){
        // 更新安装次数
        final Plug plug = plugService.getOne(new LambdaQueryWrapper<Plug>().eq(Plug::getMenuId, id));
        plug.setInstalls(plug.getInstalls() + 1);
        plugService.updateById(plug);
        return success("安装成功");
      }
      return fail("安装失败",id);
    }
}
