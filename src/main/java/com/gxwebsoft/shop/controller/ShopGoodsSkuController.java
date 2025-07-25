package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopGoodsSkuService;
import com.gxwebsoft.shop.entity.ShopGoodsSku;
import com.gxwebsoft.shop.param.ShopGoodsSkuParam;
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
 * 商品sku列表控制器
 *
 * @author 科技小王子
 * @since 2025-05-01 09:43:31
 */
@Api(tags = "商品sku列表管理")
@RestController
@RequestMapping("/api/shop/shop-goods-sku")
public class ShopGoodsSkuController extends BaseController {
    @Resource
    private ShopGoodsSkuService shopGoodsSkuService;

    @ApiOperation("分页查询商品sku列表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopGoodsSku>> page(ShopGoodsSkuParam param) {
        // 使用关联查询
        return success(shopGoodsSkuService.pageRel(param));
    }

    @ApiOperation("查询全部商品sku列表")
    @GetMapping()
    public ApiResult<List<ShopGoodsSku>> list(ShopGoodsSkuParam param) {
        // 使用关联查询
        return success(shopGoodsSkuService.listRel(param));
    }

    @ApiOperation("根据id查询商品sku列表")
    @GetMapping("/{id}")
    public ApiResult<ShopGoodsSku> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopGoodsSkuService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsSku:save')")
    @OperationLog
    @ApiOperation("添加商品sku列表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopGoodsSku shopGoodsSku) {
        if (shopGoodsSkuService.save(shopGoodsSku)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsSku:update')")
    @OperationLog
    @ApiOperation("修改商品sku列表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopGoodsSku shopGoodsSku) {
        if (shopGoodsSkuService.updateById(shopGoodsSku)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsSku:remove')")
    @OperationLog
    @ApiOperation("删除商品sku列表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopGoodsSkuService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsSku:save')")
    @OperationLog
    @ApiOperation("批量添加商品sku列表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopGoodsSku> list) {
        if (shopGoodsSkuService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsSku:update')")
    @OperationLog
    @ApiOperation("批量修改商品sku列表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopGoodsSku> batchParam) {
        if (batchParam.update(shopGoodsSkuService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsSku:remove')")
    @OperationLog
    @ApiOperation("批量删除商品sku列表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopGoodsSkuService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
