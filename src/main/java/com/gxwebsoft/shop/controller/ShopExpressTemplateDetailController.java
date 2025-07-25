package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopExpressTemplateDetailService;
import com.gxwebsoft.shop.entity.ShopExpressTemplateDetail;
import com.gxwebsoft.shop.param.ShopExpressTemplateDetailParam;
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
 * 运费模板控制器
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
@Api(tags = "运费模板管理")
@RestController
@RequestMapping("/api/shop/shop-express-template-detail")
public class ShopExpressTemplateDetailController extends BaseController {
    @Resource
    private ShopExpressTemplateDetailService shopExpressTemplateDetailService;

    @ApiOperation("分页查询运费模板")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopExpressTemplateDetail>> page(ShopExpressTemplateDetailParam param) {
        // 使用关联查询
        return success(shopExpressTemplateDetailService.pageRel(param));
    }

    @ApiOperation("查询全部运费模板")
    @GetMapping()
    public ApiResult<List<ShopExpressTemplateDetail>> list(ShopExpressTemplateDetailParam param) {
        // 使用关联查询
        return success(shopExpressTemplateDetailService.listRel(param));
    }

    @ApiOperation("根据id查询运费模板")
    @GetMapping("/{id}")
    public ApiResult<ShopExpressTemplateDetail> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopExpressTemplateDetailService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('shop:shopExpressTemplateDetail:save')")
    @OperationLog
    @ApiOperation("添加运费模板")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopExpressTemplateDetail shopExpressTemplateDetail) {
        if (shopExpressTemplateDetailService.save(shopExpressTemplateDetail)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpressTemplateDetail:update')")
    @OperationLog
    @ApiOperation("修改运费模板")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopExpressTemplateDetail shopExpressTemplateDetail) {
        if (shopExpressTemplateDetailService.updateById(shopExpressTemplateDetail)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpressTemplateDetail:remove')")
    @OperationLog
    @ApiOperation("删除运费模板")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopExpressTemplateDetailService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpressTemplateDetail:save')")
    @OperationLog
    @ApiOperation("批量添加运费模板")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopExpressTemplateDetail> list) {
        if (shopExpressTemplateDetailService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpressTemplateDetail:update')")
    @OperationLog
    @ApiOperation("批量修改运费模板")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopExpressTemplateDetail> batchParam) {
        if (batchParam.update(shopExpressTemplateDetailService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpressTemplateDetail:remove')")
    @OperationLog
    @ApiOperation("批量删除运费模板")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopExpressTemplateDetailService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
