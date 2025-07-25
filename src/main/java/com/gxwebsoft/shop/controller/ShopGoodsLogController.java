package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopGoodsLogService;
import com.gxwebsoft.shop.entity.ShopGoodsLog;
import com.gxwebsoft.shop.param.ShopGoodsLogParam;
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
 * 商品日志表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "商品日志表管理")
@RestController
@RequestMapping("/api/shop/shop-goods-log")
public class ShopGoodsLogController extends BaseController {
    @Resource
    private ShopGoodsLogService shopGoodsLogService;

    @ApiOperation("分页查询商品日志表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopGoodsLog>> page(ShopGoodsLogParam param) {
        // 使用关联查询
        return success(shopGoodsLogService.pageRel(param));
    }

    @ApiOperation("查询全部商品日志表")
    @GetMapping()
    public ApiResult<List<ShopGoodsLog>> list(ShopGoodsLogParam param) {
        // 使用关联查询
        return success(shopGoodsLogService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsLog:list')")
    @ApiOperation("根据id查询商品日志表")
    @GetMapping("/{id}")
    public ApiResult<ShopGoodsLog> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopGoodsLogService.getByIdRel(id));
    }

    @ApiOperation("添加商品日志表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopGoodsLog shopGoodsLog) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopGoodsLog.setUserId(loginUser.getUserId());
        }
        if (shopGoodsLogService.save(shopGoodsLog)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改商品日志表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopGoodsLog shopGoodsLog) {
        if (shopGoodsLogService.updateById(shopGoodsLog)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除商品日志表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopGoodsLogService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加商品日志表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopGoodsLog> list) {
        if (shopGoodsLogService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改商品日志表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopGoodsLog> batchParam) {
        if (batchParam.update(shopGoodsLogService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除商品日志表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopGoodsLogService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
