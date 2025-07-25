package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopGoodsRoleCommissionService;
import com.gxwebsoft.shop.entity.ShopGoodsRoleCommission;
import com.gxwebsoft.shop.param.ShopGoodsRoleCommissionParam;
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
 * 商品绑定角色的分润金额控制器
 *
 * @author 科技小王子
 * @since 2025-05-01 09:53:38
 */
@Api(tags = "商品绑定角色的分润金额管理")
@RestController
@RequestMapping("/api/shop/shop-goods-role-commission")
public class ShopGoodsRoleCommissionController extends BaseController {
    @Resource
    private ShopGoodsRoleCommissionService shopGoodsRoleCommissionService;

    @ApiOperation("分页查询商品绑定角色的分润金额")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopGoodsRoleCommission>> page(ShopGoodsRoleCommissionParam param) {
        // 使用关联查询
        return success(shopGoodsRoleCommissionService.pageRel(param));
    }

    @ApiOperation("查询全部商品绑定角色的分润金额")
    @GetMapping()
    public ApiResult<List<ShopGoodsRoleCommission>> list(ShopGoodsRoleCommissionParam param) {
        // 使用关联查询
        return success(shopGoodsRoleCommissionService.listRel(param));
    }

    @ApiOperation("根据id查询商品绑定角色的分润金额")
    @GetMapping("/{id}")
    public ApiResult<ShopGoodsRoleCommission> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopGoodsRoleCommissionService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsRoleCommission:save')")
    @OperationLog
    @ApiOperation("添加商品绑定角色的分润金额")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopGoodsRoleCommission shopGoodsRoleCommission) {
        if (shopGoodsRoleCommissionService.save(shopGoodsRoleCommission)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsRoleCommission:update')")
    @OperationLog
    @ApiOperation("修改商品绑定角色的分润金额")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopGoodsRoleCommission shopGoodsRoleCommission) {
        if (shopGoodsRoleCommissionService.updateById(shopGoodsRoleCommission)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsRoleCommission:remove')")
    @OperationLog
    @ApiOperation("删除商品绑定角色的分润金额")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopGoodsRoleCommissionService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsRoleCommission:save')")
    @OperationLog
    @ApiOperation("批量添加商品绑定角色的分润金额")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopGoodsRoleCommission> list) {
        if (shopGoodsRoleCommissionService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsRoleCommission:update')")
    @OperationLog
    @ApiOperation("批量修改商品绑定角色的分润金额")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopGoodsRoleCommission> batchParam) {
        if (batchParam.update(shopGoodsRoleCommissionService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsRoleCommission:remove')")
    @OperationLog
    @ApiOperation("批量删除商品绑定角色的分润金额")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopGoodsRoleCommissionService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
