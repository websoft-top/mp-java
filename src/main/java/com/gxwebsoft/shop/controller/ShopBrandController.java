package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopBrandService;
import com.gxwebsoft.shop.entity.ShopBrand;
import com.gxwebsoft.shop.param.ShopBrandParam;
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
 * 品牌控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "品牌管理")
@RestController
@RequestMapping("/api/shop/shop-brand")
public class ShopBrandController extends BaseController {
    @Resource
    private ShopBrandService shopBrandService;

    @ApiOperation("分页查询品牌")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopBrand>> page(ShopBrandParam param) {
        // 使用关联查询
        return success(shopBrandService.pageRel(param));
    }

    @ApiOperation("查询全部品牌")
    @GetMapping()
    public ApiResult<List<ShopBrand>> list(ShopBrandParam param) {
        // 使用关联查询
        return success(shopBrandService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopBrand:list')")
    @ApiOperation("根据id查询品牌")
    @GetMapping("/{id}")
    public ApiResult<ShopBrand> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopBrandService.getByIdRel(id));
    }

    @ApiOperation("添加品牌")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopBrand shopBrand) {
        if (shopBrandService.save(shopBrand)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改品牌")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopBrand shopBrand) {
        if (shopBrandService.updateById(shopBrand)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除品牌")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopBrandService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加品牌")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopBrand> list) {
        if (shopBrandService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改品牌")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopBrand> batchParam) {
        if (batchParam.update(shopBrandService, "brand_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除品牌")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopBrandService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
