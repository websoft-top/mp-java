package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopWechatDepositService;
import com.gxwebsoft.shop.entity.ShopWechatDeposit;
import com.gxwebsoft.shop.param.ShopWechatDepositParam;
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
 * 押金控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Api(tags = "押金管理")
@RestController
@RequestMapping("/api/shop/shop-wechat-deposit")
public class ShopWechatDepositController extends BaseController {
    @Resource
    private ShopWechatDepositService shopWechatDepositService;

    @ApiOperation("分页查询押金")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopWechatDeposit>> page(ShopWechatDepositParam param) {
        // 使用关联查询
        return success(shopWechatDepositService.pageRel(param));
    }

    @ApiOperation("查询全部押金")
    @GetMapping()
    public ApiResult<List<ShopWechatDeposit>> list(ShopWechatDepositParam param) {
        // 使用关联查询
        return success(shopWechatDepositService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopWechatDeposit:list')")
    @ApiOperation("根据id查询押金")
    @GetMapping("/{id}")
    public ApiResult<ShopWechatDeposit> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopWechatDepositService.getByIdRel(id));
    }

    @ApiOperation("添加押金")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopWechatDeposit shopWechatDeposit) {
        if (shopWechatDepositService.save(shopWechatDeposit)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改押金")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopWechatDeposit shopWechatDeposit) {
        if (shopWechatDepositService.updateById(shopWechatDeposit)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除押金")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopWechatDepositService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加押金")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopWechatDeposit> list) {
        if (shopWechatDepositService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改押金")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopWechatDeposit> batchParam) {
        if (batchParam.update(shopWechatDepositService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除押金")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopWechatDepositService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
