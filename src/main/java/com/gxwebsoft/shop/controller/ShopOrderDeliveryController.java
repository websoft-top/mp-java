package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopOrderDeliveryService;
import com.gxwebsoft.shop.entity.ShopOrderDelivery;
import com.gxwebsoft.shop.param.ShopOrderDeliveryParam;
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
 * 发货单控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "发货单管理")
@RestController
@RequestMapping("/api/shop/shop-order-delivery")
public class ShopOrderDeliveryController extends BaseController {
    @Resource
    private ShopOrderDeliveryService shopOrderDeliveryService;

    @ApiOperation("分页查询发货单")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopOrderDelivery>> page(ShopOrderDeliveryParam param) {
        // 使用关联查询
        return success(shopOrderDeliveryService.pageRel(param));
    }

    @ApiOperation("查询全部发货单")
    @GetMapping()
    public ApiResult<List<ShopOrderDelivery>> list(ShopOrderDeliveryParam param) {
        // 使用关联查询
        return success(shopOrderDeliveryService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopOrderDelivery:list')")
    @ApiOperation("根据id查询发货单")
    @GetMapping("/{id}")
    public ApiResult<ShopOrderDelivery> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopOrderDeliveryService.getByIdRel(id));
    }

    @ApiOperation("添加发货单")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopOrderDelivery shopOrderDelivery) {
        if (shopOrderDeliveryService.save(shopOrderDelivery)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改发货单")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopOrderDelivery shopOrderDelivery) {
        if (shopOrderDeliveryService.updateById(shopOrderDelivery)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除发货单")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopOrderDeliveryService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加发货单")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopOrderDelivery> list) {
        if (shopOrderDeliveryService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改发货单")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopOrderDelivery> batchParam) {
        if (batchParam.update(shopOrderDeliveryService, "delivery_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除发货单")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopOrderDeliveryService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
