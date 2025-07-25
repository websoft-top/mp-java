package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopOrderGoodsService;
import com.gxwebsoft.shop.entity.ShopOrderGoods;
import com.gxwebsoft.shop.param.ShopOrderGoodsParam;
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
 * 商品信息控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "商品信息管理")
@RestController
@RequestMapping("/api/shop/shop-order-goods")
public class ShopOrderGoodsController extends BaseController {
    @Resource
    private ShopOrderGoodsService shopOrderGoodsService;

    @ApiOperation("分页查询商品信息")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopOrderGoods>> page(ShopOrderGoodsParam param) {
        // 使用关联查询
        return success(shopOrderGoodsService.pageRel(param));
    }

    @ApiOperation("查询全部商品信息")
    @GetMapping()
    public ApiResult<List<ShopOrderGoods>> list(ShopOrderGoodsParam param) {
        // 使用关联查询
        return success(shopOrderGoodsService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopOrderGoods:list')")
    @ApiOperation("根据id查询商品信息")
    @GetMapping("/{id}")
    public ApiResult<ShopOrderGoods> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopOrderGoodsService.getByIdRel(id));
    }

    @ApiOperation("添加商品信息")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopOrderGoods shopOrderGoods) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopOrderGoods.setUserId(loginUser.getUserId());
        }
        if (shopOrderGoodsService.save(shopOrderGoods)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改商品信息")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopOrderGoods shopOrderGoods) {
        if (shopOrderGoodsService.updateById(shopOrderGoods)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除商品信息")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopOrderGoodsService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加商品信息")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopOrderGoods> list) {
        if (shopOrderGoodsService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改商品信息")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopOrderGoods> batchParam) {
        if (batchParam.update(shopOrderGoodsService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除商品信息")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopOrderGoodsService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
