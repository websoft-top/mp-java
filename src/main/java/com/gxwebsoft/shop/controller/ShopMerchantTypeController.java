package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopMerchantTypeService;
import com.gxwebsoft.shop.entity.ShopMerchantType;
import com.gxwebsoft.shop.param.ShopMerchantTypeParam;
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
 * 商户类型控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "商户类型管理")
@RestController
@RequestMapping("/api/shop/shop-merchant-type")
public class ShopMerchantTypeController extends BaseController {
    @Resource
    private ShopMerchantTypeService shopMerchantTypeService;

    @ApiOperation("分页查询商户类型")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopMerchantType>> page(ShopMerchantTypeParam param) {
        // 使用关联查询
        return success(shopMerchantTypeService.pageRel(param));
    }

    @ApiOperation("查询全部商户类型")
    @GetMapping()
    public ApiResult<List<ShopMerchantType>> list(ShopMerchantTypeParam param) {
        // 使用关联查询
        return success(shopMerchantTypeService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopMerchantType:list')")
    @ApiOperation("根据id查询商户类型")
    @GetMapping("/{id}")
    public ApiResult<ShopMerchantType> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopMerchantTypeService.getByIdRel(id));
    }

    @ApiOperation("添加商户类型")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopMerchantType shopMerchantType) {
        if (shopMerchantTypeService.save(shopMerchantType)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改商户类型")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopMerchantType shopMerchantType) {
        if (shopMerchantTypeService.updateById(shopMerchantType)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除商户类型")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopMerchantTypeService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加商户类型")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopMerchantType> list) {
        if (shopMerchantTypeService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改商户类型")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopMerchantType> batchParam) {
        if (batchParam.update(shopMerchantTypeService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除商户类型")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopMerchantTypeService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
