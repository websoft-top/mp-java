package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopGoodsSpecService;
import com.gxwebsoft.shop.entity.ShopGoodsSpec;
import com.gxwebsoft.shop.param.ShopGoodsSpecParam;
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
 * 商品多规格控制器
 *
 * @author 科技小王子
 * @since 2025-05-01 09:43:31
 */
@Api(tags = "商品多规格管理")
@RestController
@RequestMapping("/api/shop/shop-goods-spec")
public class ShopGoodsSpecController extends BaseController {
    @Resource
    private ShopGoodsSpecService shopGoodsSpecService;

    @ApiOperation("分页查询商品多规格")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopGoodsSpec>> page(ShopGoodsSpecParam param) {
        // 使用关联查询
        return success(shopGoodsSpecService.pageRel(param));
    }

    @ApiOperation("查询全部商品多规格")
    @GetMapping()
    public ApiResult<List<ShopGoodsSpec>> list(ShopGoodsSpecParam param) {
        // 使用关联查询
        return success(shopGoodsSpecService.listRel(param));
    }

    @ApiOperation("根据id查询商品多规格")
    @GetMapping("/{id}")
    public ApiResult<ShopGoodsSpec> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopGoodsSpecService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsSpec:save')")
    @OperationLog
    @ApiOperation("添加商品多规格")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopGoodsSpec shopGoodsSpec) {
        if (shopGoodsSpecService.save(shopGoodsSpec)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsSpec:update')")
    @OperationLog
    @ApiOperation("修改商品多规格")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopGoodsSpec shopGoodsSpec) {
        if (shopGoodsSpecService.updateById(shopGoodsSpec)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsSpec:remove')")
    @OperationLog
    @ApiOperation("删除商品多规格")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopGoodsSpecService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsSpec:save')")
    @OperationLog
    @ApiOperation("批量添加商品多规格")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopGoodsSpec> list) {
        if (shopGoodsSpecService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsSpec:update')")
    @OperationLog
    @ApiOperation("批量修改商品多规格")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopGoodsSpec> batchParam) {
        if (batchParam.update(shopGoodsSpecService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsSpec:remove')")
    @OperationLog
    @ApiOperation("批量删除商品多规格")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopGoodsSpecService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
