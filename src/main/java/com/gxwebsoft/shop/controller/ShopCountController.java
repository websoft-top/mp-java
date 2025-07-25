package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopCountService;
import com.gxwebsoft.shop.entity.ShopCount;
import com.gxwebsoft.shop.param.ShopCountParam;
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
 * 商城销售统计表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "商城销售统计表管理")
@RestController
@RequestMapping("/api/shop/shop-count")
public class ShopCountController extends BaseController {
    @Resource
    private ShopCountService shopCountService;

    @ApiOperation("分页查询商城销售统计表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopCount>> page(ShopCountParam param) {
        // 使用关联查询
        return success(shopCountService.pageRel(param));
    }

    @ApiOperation("查询全部商城销售统计表")
    @GetMapping()
    public ApiResult<List<ShopCount>> list(ShopCountParam param) {
        // 使用关联查询
        return success(shopCountService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopCount:list')")
    @ApiOperation("根据id查询商城销售统计表")
    @GetMapping("/{id}")
    public ApiResult<ShopCount> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopCountService.getByIdRel(id));
    }

    @ApiOperation("添加商城销售统计表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopCount shopCount) {
        if (shopCountService.save(shopCount)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改商城销售统计表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopCount shopCount) {
        if (shopCountService.updateById(shopCount)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除商城销售统计表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopCountService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加商城销售统计表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopCount> list) {
        if (shopCountService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改商城销售统计表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopCount> batchParam) {
        if (batchParam.update(shopCountService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除商城销售统计表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopCountService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
