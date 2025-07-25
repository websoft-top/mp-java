package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopGoodsIncomeConfigService;
import com.gxwebsoft.shop.entity.ShopGoodsIncomeConfig;
import com.gxwebsoft.shop.param.ShopGoodsIncomeConfigParam;
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
 * 分润配置控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "分润配置管理")
@RestController
@RequestMapping("/api/shop/shop-goods-income-config")
public class ShopGoodsIncomeConfigController extends BaseController {
    @Resource
    private ShopGoodsIncomeConfigService shopGoodsIncomeConfigService;

    @ApiOperation("分页查询分润配置")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopGoodsIncomeConfig>> page(ShopGoodsIncomeConfigParam param) {
        // 使用关联查询
        return success(shopGoodsIncomeConfigService.pageRel(param));
    }

    @ApiOperation("查询全部分润配置")
    @GetMapping()
    public ApiResult<List<ShopGoodsIncomeConfig>> list(ShopGoodsIncomeConfigParam param) {
        // 使用关联查询
        return success(shopGoodsIncomeConfigService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsIncomeConfig:list')")
    @ApiOperation("根据id查询分润配置")
    @GetMapping("/{id}")
    public ApiResult<ShopGoodsIncomeConfig> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopGoodsIncomeConfigService.getByIdRel(id));
    }

    @ApiOperation("添加分润配置")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopGoodsIncomeConfig shopGoodsIncomeConfig) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopGoodsIncomeConfig.setUserId(loginUser.getUserId());
        }
        if (shopGoodsIncomeConfigService.save(shopGoodsIncomeConfig)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改分润配置")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopGoodsIncomeConfig shopGoodsIncomeConfig) {
        if (shopGoodsIncomeConfigService.updateById(shopGoodsIncomeConfig)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除分润配置")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopGoodsIncomeConfigService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加分润配置")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopGoodsIncomeConfig> list) {
        if (shopGoodsIncomeConfigService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改分润配置")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopGoodsIncomeConfig> batchParam) {
        if (batchParam.update(shopGoodsIncomeConfigService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除分润配置")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopGoodsIncomeConfigService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
