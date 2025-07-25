package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopGoodsCouponService;
import com.gxwebsoft.shop.entity.ShopGoodsCoupon;
import com.gxwebsoft.shop.param.ShopGoodsCouponParam;
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
 * 商品优惠券表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "商品优惠券表管理")
@RestController
@RequestMapping("/api/shop/shop-goods-coupon")
public class ShopGoodsCouponController extends BaseController {
    @Resource
    private ShopGoodsCouponService shopGoodsCouponService;

    @ApiOperation("分页查询商品优惠券表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopGoodsCoupon>> page(ShopGoodsCouponParam param) {
        // 使用关联查询
        return success(shopGoodsCouponService.pageRel(param));
    }

    @ApiOperation("查询全部商品优惠券表")
    @GetMapping()
    public ApiResult<List<ShopGoodsCoupon>> list(ShopGoodsCouponParam param) {
        // 使用关联查询
        return success(shopGoodsCouponService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsCoupon:list')")
    @ApiOperation("根据id查询商品优惠券表")
    @GetMapping("/{id}")
    public ApiResult<ShopGoodsCoupon> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopGoodsCouponService.getByIdRel(id));
    }

    @ApiOperation("添加商品优惠券表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopGoodsCoupon shopGoodsCoupon) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopGoodsCoupon.setUserId(loginUser.getUserId());
        }
        if (shopGoodsCouponService.save(shopGoodsCoupon)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改商品优惠券表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopGoodsCoupon shopGoodsCoupon) {
        if (shopGoodsCouponService.updateById(shopGoodsCoupon)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除商品优惠券表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopGoodsCouponService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加商品优惠券表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopGoodsCoupon> list) {
        if (shopGoodsCouponService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改商品优惠券表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopGoodsCoupon> batchParam) {
        if (batchParam.update(shopGoodsCouponService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除商品优惠券表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopGoodsCouponService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
