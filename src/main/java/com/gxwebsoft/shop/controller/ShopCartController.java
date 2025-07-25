package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopCartService;
import com.gxwebsoft.shop.entity.ShopCart;
import com.gxwebsoft.shop.param.ShopCartParam;
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
 * 购物车控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "购物车管理")
@RestController
@RequestMapping("/api/shop/shop-cart")
public class ShopCartController extends BaseController {
    @Resource
    private ShopCartService shopCartService;

    @ApiOperation("分页查询购物车")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopCart>> page(ShopCartParam param) {
        // 使用关联查询
        return success(shopCartService.pageRel(param));
    }

    @ApiOperation("查询全部购物车")
    @GetMapping()
    public ApiResult<List<ShopCart>> list(ShopCartParam param) {
        // 使用关联查询
        return success(shopCartService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopCart:list')")
    @ApiOperation("根据id查询购物车")
    @GetMapping("/{id}")
    public ApiResult<ShopCart> get(@PathVariable("id") Long id) {
        // 使用关联查询
        return success(shopCartService.getByIdRel(id));
    }

    @ApiOperation("添加购物车")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopCart shopCart) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopCart.setUserId(loginUser.getUserId());
        }
        if (shopCartService.save(shopCart)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改购物车")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopCart shopCart) {
        if (shopCartService.updateById(shopCart)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除购物车")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopCartService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加购物车")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopCart> list) {
        if (shopCartService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改购物车")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopCart> batchParam) {
        if (batchParam.update(shopCartService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除购物车")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopCartService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
