package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopExpressService;
import com.gxwebsoft.shop.entity.ShopExpress;
import com.gxwebsoft.shop.param.ShopExpressParam;
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
 * 物流公司控制器
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
@Api(tags = "物流公司管理")
@RestController
@RequestMapping("/api/shop/shop-express")
public class ShopExpressController extends BaseController {
    @Resource
    private ShopExpressService shopExpressService;

    @ApiOperation("分页查询物流公司")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopExpress>> page(ShopExpressParam param) {
        // 使用关联查询
        return success(shopExpressService.pageRel(param));
    }

    @ApiOperation("查询全部物流公司")
    @GetMapping()
    public ApiResult<List<ShopExpress>> list(ShopExpressParam param) {
        // 使用关联查询
        return success(shopExpressService.listRel(param));
    }

    @ApiOperation("根据id查询物流公司")
    @GetMapping("/{id}")
    public ApiResult<ShopExpress> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopExpressService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('shop:shopExpress:save')")
    @OperationLog
    @ApiOperation("添加物流公司")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopExpress shopExpress) {
        if (shopExpressService.save(shopExpress)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpress:update')")
    @OperationLog
    @ApiOperation("修改物流公司")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopExpress shopExpress) {
        if (shopExpressService.updateById(shopExpress)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpress:remove')")
    @OperationLog
    @ApiOperation("删除物流公司")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopExpressService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpress:save')")
    @OperationLog
    @ApiOperation("批量添加物流公司")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopExpress> list) {
        if (shopExpressService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpress:update')")
    @OperationLog
    @ApiOperation("批量修改物流公司")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopExpress> batchParam) {
        if (batchParam.update(shopExpressService, "express_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpress:remove')")
    @OperationLog
    @ApiOperation("批量删除物流公司")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopExpressService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
