package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopRechargeOrderService;
import com.gxwebsoft.shop.entity.ShopRechargeOrder;
import com.gxwebsoft.shop.param.ShopRechargeOrderParam;
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
 * 会员充值订单表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "会员充值订单表管理")
@RestController
@RequestMapping("/api/shop/shop-recharge-order")
public class ShopRechargeOrderController extends BaseController {
    @Resource
    private ShopRechargeOrderService shopRechargeOrderService;

    @ApiOperation("分页查询会员充值订单表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopRechargeOrder>> page(ShopRechargeOrderParam param) {
        // 使用关联查询
        return success(shopRechargeOrderService.pageRel(param));
    }

    @ApiOperation("查询全部会员充值订单表")
    @GetMapping()
    public ApiResult<List<ShopRechargeOrder>> list(ShopRechargeOrderParam param) {
        // 使用关联查询
        return success(shopRechargeOrderService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopRechargeOrder:list')")
    @ApiOperation("根据id查询会员充值订单表")
    @GetMapping("/{id}")
    public ApiResult<ShopRechargeOrder> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopRechargeOrderService.getByIdRel(id));
    }

    @ApiOperation("添加会员充值订单表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopRechargeOrder shopRechargeOrder) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopRechargeOrder.setUserId(loginUser.getUserId());
        }
        if (shopRechargeOrderService.save(shopRechargeOrder)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改会员充值订单表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopRechargeOrder shopRechargeOrder) {
        if (shopRechargeOrderService.updateById(shopRechargeOrder)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除会员充值订单表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopRechargeOrderService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加会员充值订单表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopRechargeOrder> list) {
        if (shopRechargeOrderService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改会员充值订单表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopRechargeOrder> batchParam) {
        if (batchParam.update(shopRechargeOrderService, "order_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除会员充值订单表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopRechargeOrderService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
