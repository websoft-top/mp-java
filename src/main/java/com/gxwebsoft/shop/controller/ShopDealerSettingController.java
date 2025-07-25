package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopDealerSettingService;
import com.gxwebsoft.shop.entity.ShopDealerSetting;
import com.gxwebsoft.shop.param.ShopDealerSettingParam;
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
 * 分销商设置表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "分销商设置表管理")
@RestController
@RequestMapping("/api/shop/shop-dealer-setting")
public class ShopDealerSettingController extends BaseController {
    @Resource
    private ShopDealerSettingService shopDealerSettingService;

    @ApiOperation("分页查询分销商设置表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopDealerSetting>> page(ShopDealerSettingParam param) {
        // 使用关联查询
        return success(shopDealerSettingService.pageRel(param));
    }

    @ApiOperation("查询全部分销商设置表")
    @GetMapping()
    public ApiResult<List<ShopDealerSetting>> list(ShopDealerSettingParam param) {
        // 使用关联查询
        return success(shopDealerSettingService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopDealerSetting:list')")
    @ApiOperation("根据id查询分销商设置表")
    @GetMapping("/{id}")
    public ApiResult<ShopDealerSetting> get(@PathVariable("id") String id) {
        // 使用关联查询
        return success(shopDealerSettingService.getByIdRel(id));
    }

    @ApiOperation("添加分销商设置表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopDealerSetting shopDealerSetting) {
        if (shopDealerSettingService.save(shopDealerSetting)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改分销商设置表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopDealerSetting shopDealerSetting) {
        if (shopDealerSettingService.updateById(shopDealerSetting)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除分销商设置表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopDealerSettingService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加分销商设置表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopDealerSetting> list) {
        if (shopDealerSettingService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改分销商设置表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopDealerSetting> batchParam) {
        if (batchParam.update(shopDealerSettingService, "key")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除分销商设置表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopDealerSettingService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
