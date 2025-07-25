package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopCommissionRoleService;
import com.gxwebsoft.shop.entity.ShopCommissionRole;
import com.gxwebsoft.shop.param.ShopCommissionRoleParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.system.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分红角色控制器
 *
 * @author 科技小王子
 * @since 2025-05-01 10:01:15
 */
@Api(tags = "分红角色管理")
@RestController
@RequestMapping("/api/shop/shop-commission-role")
public class ShopCommissionRoleController extends BaseController {
    @Resource
    private ShopCommissionRoleService shopCommissionRoleService;

    @ApiOperation("分页查询分红角色")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopCommissionRole>> page(ShopCommissionRoleParam param) {
        // 使用关联查询
        return success(shopCommissionRoleService.pageRel(param));
    }

    @ApiOperation("查询全部分红角色")
    @GetMapping()
    public ApiResult<List<ShopCommissionRole>> list(ShopCommissionRoleParam param) {
        // 使用关联查询
        return success(shopCommissionRoleService.listRel(param));
    }

    @ApiOperation("根据id查询分红角色")
    @GetMapping("/{id}")
    public ApiResult<ShopCommissionRole> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopCommissionRoleService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('shop:shopCommissionRole:save')")
    @OperationLog
    @ApiOperation("添加分红角色")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopCommissionRole shopCommissionRole) {
        if (shopCommissionRoleService.save(shopCommissionRole)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopCommissionRole:update')")
    @OperationLog
    @ApiOperation("修改分红角色")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopCommissionRole shopCommissionRole) {
        if (shopCommissionRoleService.updateById(shopCommissionRole)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopCommissionRole:remove')")
    @OperationLog
    @ApiOperation("删除分红角色")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopCommissionRoleService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('shop:shopCommissionRole:save')")
    @OperationLog
    @ApiOperation("批量添加分红角色")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopCommissionRole> list) {
        if (shopCommissionRoleService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopCommissionRole:update')")
    @OperationLog
    @ApiOperation("批量修改分红角色")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopCommissionRole> batchParam) {
        if (batchParam.update(shopCommissionRoleService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopCommissionRole:remove')")
    @OperationLog
    @ApiOperation("批量删除分红角色")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopCommissionRoleService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
