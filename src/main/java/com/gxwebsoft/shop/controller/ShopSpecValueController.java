package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopSpecValueService;
import com.gxwebsoft.shop.entity.ShopSpecValue;
import com.gxwebsoft.shop.param.ShopSpecValueParam;
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
 * 规格值控制器
 *
 * @author 科技小王子
 * @since 2025-05-01 09:44:00
 */
@Api(tags = "规格值管理")
@RestController
@RequestMapping("/api/shop/shop-spec-value")
public class ShopSpecValueController extends BaseController {
    @Resource
    private ShopSpecValueService shopSpecValueService;

    @ApiOperation("分页查询规格值")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopSpecValue>> page(ShopSpecValueParam param) {
        // 使用关联查询
        return success(shopSpecValueService.pageRel(param));
    }

    @ApiOperation("查询全部规格值")
    @GetMapping()
    public ApiResult<List<ShopSpecValue>> list(ShopSpecValueParam param) {
        // 使用关联查询
        return success(shopSpecValueService.listRel(param));
    }

    @ApiOperation("根据id查询规格值")
    @GetMapping("/{id}")
    public ApiResult<ShopSpecValue> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopSpecValueService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('shop:shopSpecValue:save')")
    @OperationLog
    @ApiOperation("添加规格值")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopSpecValue shopSpecValue) {
        if (shopSpecValueService.save(shopSpecValue)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopSpecValue:update')")
    @OperationLog
    @ApiOperation("修改规格值")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopSpecValue shopSpecValue) {
        if (shopSpecValueService.updateById(shopSpecValue)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopSpecValue:remove')")
    @OperationLog
    @ApiOperation("删除规格值")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopSpecValueService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('shop:shopSpecValue:save')")
    @OperationLog
    @ApiOperation("批量添加规格值")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopSpecValue> list) {
        if (shopSpecValueService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopSpecValue:update')")
    @OperationLog
    @ApiOperation("批量修改规格值")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopSpecValue> batchParam) {
        if (batchParam.update(shopSpecValueService, "spec_value_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopSpecValue:remove')")
    @OperationLog
    @ApiOperation("批量删除规格值")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopSpecValueService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
