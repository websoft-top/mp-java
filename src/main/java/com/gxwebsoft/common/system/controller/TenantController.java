package com.gxwebsoft.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gxwebsoft.common.core.exception.BusinessException;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.system.entity.Menu;
import com.gxwebsoft.common.system.entity.RoleMenu;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.service.MenuService;
import com.gxwebsoft.common.system.service.RoleMenuService;
import com.gxwebsoft.common.system.service.TenantService;
import com.gxwebsoft.common.system.entity.Tenant;
import com.gxwebsoft.common.system.param.TenantParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.annotation.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 租户控制器
 *
 * @author 科技小王子
 * @since 2023-07-17 17:49:53
 */
@Api(tags = "租户管理")
@RestController
@RequestMapping("/api/system/tenant")
public class TenantController extends BaseController {
    @Resource
    private TenantService tenantService;
    @Resource
    private MenuService menuService;
    @Resource
    private RoleMenuService roleMenuService;

    @PreAuthorize("hasAuthority('sys:tenant:list')")
    @ApiOperation("分页查询租户")
    @GetMapping("/page")
    public ApiResult<PageResult<Tenant>> page(TenantParam param) {
        // 使用关联查询
        return success(tenantService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:tenant:list')")
    @ApiOperation("查询全部租户")
    @GetMapping()
    public ApiResult<List<Tenant>> list(TenantParam param) {
        // 使用关联查询
        return success(tenantService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:tenant:list')")
    @ApiOperation("根据id查询租户")
    @GetMapping("/{id}")
    public ApiResult<Tenant> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(tenantService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:tenant:save')")
    @ApiOperation("添加租户")
    @PostMapping()
    public ApiResult<?> save(@RequestBody Tenant tenant) {
        System.out.println("tenant = " + tenant);
        if (tenantService.save(tenant)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:tenant:update')")
    @ApiOperation("修改租户")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Tenant tenant) {
        if (tenantService.updateById(tenant)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:tenant:remove')")
    @ApiOperation("删除租户")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (tenantService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:tenant:save')")
    @ApiOperation("批量添加租户")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<Tenant> list) {
        if (tenantService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:tenant:update')")
    @ApiOperation("批量修改租户")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<Tenant> batchParam) {
        if (batchParam.update(tenantService, "tenant_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:tenant:remove')")
    @ApiOperation("批量删除租户")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (tenantService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

  @ApiOperation("租户角色权限初始化")
  @GetMapping("/role-menu/{id}")
  public ApiResult<List<Menu>> initialization(@PathVariable("id") Integer roleId) {
    List<Menu> menus = menuService.list(new LambdaQueryWrapper<Menu>().orderByAsc(Menu::getSortNumber));
    List<RoleMenu> roleMenus = roleMenuService.list(new LambdaQueryWrapper<RoleMenu>()
      .eq(RoleMenu::getRoleId, roleId));
    for (Menu menu : menus) {
      menu.setChecked(roleMenus.stream().anyMatch((d) -> d.getMenuId().equals(menu.getMenuId())));
    }
    List<Integer> menuIds = menus.stream().map(Menu::getMenuId).collect(Collectors.toList());
    roleMenuService.remove(new LambdaUpdateWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
    if (menuIds.size() > 0) {
      List<RoleMenu> roleMenuList = new ArrayList<>();
      for (Integer menuId : menuIds) {
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);
        roleMenuList.add(roleMenu);
      }
      if (!roleMenuService.saveBatch(roleMenuList)) {
        throw new BusinessException("保存失败");
      }
    }
    return success(menus);
  }

}
