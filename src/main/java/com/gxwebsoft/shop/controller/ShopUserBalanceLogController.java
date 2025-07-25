package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopUserBalanceLogService;
import com.gxwebsoft.shop.entity.ShopUserBalanceLog;
import com.gxwebsoft.shop.param.ShopUserBalanceLogParam;
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
 * 用户余额变动明细表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Api(tags = "用户余额变动明细表管理")
@RestController
@RequestMapping("/api/shop/shop-user-balance-log")
public class ShopUserBalanceLogController extends BaseController {
    @Resource
    private ShopUserBalanceLogService shopUserBalanceLogService;

    @ApiOperation("分页查询用户余额变动明细表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopUserBalanceLog>> page(ShopUserBalanceLogParam param) {
        // 使用关联查询
        return success(shopUserBalanceLogService.pageRel(param));
    }

    @ApiOperation("查询全部用户余额变动明细表")
    @GetMapping()
    public ApiResult<List<ShopUserBalanceLog>> list(ShopUserBalanceLogParam param) {
        // 使用关联查询
        return success(shopUserBalanceLogService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopUserBalanceLog:list')")
    @ApiOperation("根据id查询用户余额变动明细表")
    @GetMapping("/{id}")
    public ApiResult<ShopUserBalanceLog> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopUserBalanceLogService.getByIdRel(id));
    }

    @ApiOperation("添加用户余额变动明细表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopUserBalanceLog shopUserBalanceLog) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopUserBalanceLog.setUserId(loginUser.getUserId());
        }
        if (shopUserBalanceLogService.save(shopUserBalanceLog)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改用户余额变动明细表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopUserBalanceLog shopUserBalanceLog) {
        if (shopUserBalanceLogService.updateById(shopUserBalanceLog)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除用户余额变动明细表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopUserBalanceLogService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加用户余额变动明细表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopUserBalanceLog> list) {
        if (shopUserBalanceLogService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改用户余额变动明细表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopUserBalanceLog> batchParam) {
        if (batchParam.update(shopUserBalanceLogService, "log_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除用户余额变动明细表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopUserBalanceLogService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
