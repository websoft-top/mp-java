package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopOrderDeliveryGoodsService;
import com.gxwebsoft.shop.entity.ShopOrderDeliveryGoods;
import com.gxwebsoft.shop.param.ShopOrderDeliveryGoodsParam;
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
 * 发货单商品控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "发货单商品管理")
@RestController
@RequestMapping("/api/shop/shop-order-delivery-goods")
public class ShopOrderDeliveryGoodsController extends BaseController {
    @Resource
    private ShopOrderDeliveryGoodsService shopOrderDeliveryGoodsService;

    @ApiOperation("分页查询发货单商品")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopOrderDeliveryGoods>> page(ShopOrderDeliveryGoodsParam param) {
        // 使用关联查询
        return success(shopOrderDeliveryGoodsService.pageRel(param));
    }

    @ApiOperation("查询全部发货单商品")
    @GetMapping()
    public ApiResult<List<ShopOrderDeliveryGoods>> list(ShopOrderDeliveryGoodsParam param) {
        // 使用关联查询
        return success(shopOrderDeliveryGoodsService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopOrderDeliveryGoods:list')")
    @ApiOperation("根据id查询发货单商品")
    @GetMapping("/{id}")
    public ApiResult<ShopOrderDeliveryGoods> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopOrderDeliveryGoodsService.getByIdRel(id));
    }

    @ApiOperation("添加发货单商品")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopOrderDeliveryGoods shopOrderDeliveryGoods) {
        if (shopOrderDeliveryGoodsService.save(shopOrderDeliveryGoods)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改发货单商品")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopOrderDeliveryGoods shopOrderDeliveryGoods) {
        if (shopOrderDeliveryGoodsService.updateById(shopOrderDeliveryGoods)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除发货单商品")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopOrderDeliveryGoodsService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加发货单商品")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopOrderDeliveryGoods> list) {
        if (shopOrderDeliveryGoodsService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改发货单商品")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopOrderDeliveryGoods> batchParam) {
        if (batchParam.update(shopOrderDeliveryGoodsService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除发货单商品")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopOrderDeliveryGoodsService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
