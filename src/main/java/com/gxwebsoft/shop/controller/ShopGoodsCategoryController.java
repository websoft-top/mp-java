package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopGoodsCategoryService;
import com.gxwebsoft.shop.entity.ShopGoodsCategory;
import com.gxwebsoft.shop.param.ShopGoodsCategoryParam;
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
 * 商品分类控制器
 *
 * @author 科技小王子
 * @since 2025-05-01 00:36:45
 */
@Api(tags = "商品分类管理")
@RestController
@RequestMapping("/api/shop/shop-goods-category")
public class ShopGoodsCategoryController extends BaseController {
    @Resource
    private ShopGoodsCategoryService shopGoodsCategoryService;

    @PreAuthorize("hasAuthority('shop:shopGoodsCategory:list')")
    @ApiOperation("分页查询商品分类")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopGoodsCategory>> page(ShopGoodsCategoryParam param) {
        // 使用关联查询
        return success(shopGoodsCategoryService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsCategory:list')")
    @ApiOperation("查询全部商品分类")
    @GetMapping()
    public ApiResult<List<ShopGoodsCategory>> list(ShopGoodsCategoryParam param) {
        // 使用关联查询
        return success(shopGoodsCategoryService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsCategory:list')")
    @ApiOperation("根据id查询商品分类")
    @GetMapping("/{id}")
    public ApiResult<ShopGoodsCategory> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopGoodsCategoryService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsCategory:save')")
    @OperationLog
    @ApiOperation("添加商品分类")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopGoodsCategory shopGoodsCategory) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopGoodsCategory.setUserId(loginUser.getUserId());
        }
        if (shopGoodsCategoryService.save(shopGoodsCategory)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsCategory:update')")
    @OperationLog
    @ApiOperation("修改商品分类")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopGoodsCategory shopGoodsCategory) {
        if (shopGoodsCategoryService.updateById(shopGoodsCategory)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsCategory:remove')")
    @OperationLog
    @ApiOperation("删除商品分类")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopGoodsCategoryService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsCategory:save')")
    @OperationLog
    @ApiOperation("批量添加商品分类")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopGoodsCategory> list) {
        if (shopGoodsCategoryService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsCategory:update')")
    @OperationLog
    @ApiOperation("批量修改商品分类")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopGoodsCategory> batchParam) {
        if (batchParam.update(shopGoodsCategoryService, "category_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopGoodsCategory:remove')")
    @OperationLog
    @ApiOperation("批量删除商品分类")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopGoodsCategoryService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
