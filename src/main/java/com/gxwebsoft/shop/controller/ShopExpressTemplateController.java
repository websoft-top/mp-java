package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopExpressTemplateService;
import com.gxwebsoft.shop.entity.ShopExpressTemplate;
import com.gxwebsoft.shop.param.ShopExpressTemplateParam;
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
@RequestMapping("/api/shop/shop-express-template")
public class ShopExpressTemplateController extends BaseController {
    @Resource
    private ShopExpressTemplateService shopExpressTemplateService;

    @ApiOperation("分页查询运费模板")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopExpressTemplate>> page(ShopExpressTemplateParam param) {
        // 使用关联查询
        return success(shopExpressTemplateService.pageRel(param));
    }

    @ApiOperation("查询全部运费模板")
    @GetMapping()
    public ApiResult<List<ShopExpressTemplate>> list(ShopExpressTemplateParam param) {
        // 使用关联查询
        return success(shopExpressTemplateService.listRel(param));
    }

    @ApiOperation("根据id查询运费模板")
    @GetMapping("/{id}")
    public ApiResult<ShopExpressTemplate> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopExpressTemplateService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('shop:shopExpressTemplate:save')")
    @OperationLog
    @ApiOperation("添加运费模板")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopExpressTemplate shopExpressTemplate) {
        if (shopExpressTemplateService.save(shopExpressTemplate)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpressTemplate:update')")
    @OperationLog
    @ApiOperation("修改运费模板")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopExpressTemplate shopExpressTemplate) {
        if (shopExpressTemplateService.updateById(shopExpressTemplate)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpressTemplate:remove')")
    @OperationLog
    @ApiOperation("删除运费模板")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopExpressTemplateService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpressTemplate:save')")
    @OperationLog
    @ApiOperation("批量添加运费模板")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopExpressTemplate> list) {
        if (shopExpressTemplateService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpressTemplate:update')")
    @OperationLog
    @ApiOperation("批量修改运费模板")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopExpressTemplate> batchParam) {
        if (batchParam.update(shopExpressTemplateService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopExpressTemplate:remove')")
    @OperationLog
    @ApiOperation("批量删除运费模板")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopExpressTemplateService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
