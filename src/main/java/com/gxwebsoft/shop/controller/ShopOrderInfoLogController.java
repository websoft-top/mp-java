package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopOrderInfoLogService;
import com.gxwebsoft.shop.entity.ShopOrderInfoLog;
import com.gxwebsoft.shop.param.ShopOrderInfoLogParam;
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
 * 订单核销控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "订单核销管理")
@RestController
@RequestMapping("/api/shop/shop-order-info-log")
public class ShopOrderInfoLogController extends BaseController {
    @Resource
    private ShopOrderInfoLogService shopOrderInfoLogService;

    @ApiOperation("分页查询订单核销")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopOrderInfoLog>> page(ShopOrderInfoLogParam param) {
        // 使用关联查询
        return success(shopOrderInfoLogService.pageRel(param));
    }

    @ApiOperation("查询全部订单核销")
    @GetMapping()
    public ApiResult<List<ShopOrderInfoLog>> list(ShopOrderInfoLogParam param) {
        // 使用关联查询
        return success(shopOrderInfoLogService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopOrderInfoLog:list')")
    @ApiOperation("根据id查询订单核销")
    @GetMapping("/{id}")
    public ApiResult<ShopOrderInfoLog> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopOrderInfoLogService.getByIdRel(id));
    }

    @ApiOperation("添加订单核销")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopOrderInfoLog shopOrderInfoLog) {
        if (shopOrderInfoLogService.save(shopOrderInfoLog)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改订单核销")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopOrderInfoLog shopOrderInfoLog) {
        if (shopOrderInfoLogService.updateById(shopOrderInfoLog)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除订单核销")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopOrderInfoLogService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加订单核销")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopOrderInfoLog> list) {
        if (shopOrderInfoLogService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改订单核销")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopOrderInfoLog> batchParam) {
        if (batchParam.update(shopOrderInfoLogService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除订单核销")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopOrderInfoLogService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
