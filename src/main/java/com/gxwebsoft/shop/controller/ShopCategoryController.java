package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopCategoryService;
import com.gxwebsoft.shop.entity.ShopCategory;
import com.gxwebsoft.shop.param.ShopCategoryParam;
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
 * @since 2025-04-24 20:52:13
 */
@Api(tags = "商品分类管理")
@RestController
@RequestMapping("/api/shop/shop-category")
public class ShopCategoryController extends BaseController {
    @Resource
    private ShopCategoryService shopCategoryService;

    @ApiOperation("分页查询商品分类")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopCategory>> page(ShopCategoryParam param) {
        // 使用关联查询
        return success(shopCategoryService.pageRel(param));
    }

    @ApiOperation("查询全部商品分类")
    @GetMapping()
    public ApiResult<List<ShopCategory>> list(ShopCategoryParam param) {
        // 使用关联查询
        return success(shopCategoryService.listRel(param));
    }

    @ApiOperation("根据id查询商品分类")
    @GetMapping("/{id}")
    public ApiResult<ShopCategory> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopCategoryService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('shop:shopCategory:save')")
    @OperationLog
    @ApiOperation("添加商品分类")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopCategory shopCategory) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopCategory.setUserId(loginUser.getUserId());
        }
        if (shopCategoryService.save(shopCategory)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopCategory:update')")
    @OperationLog
    @ApiOperation("修改商品分类")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopCategory shopCategory) {
        if (shopCategoryService.updateById(shopCategory)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopCategory:remove')")
    @OperationLog
    @ApiOperation("删除商品分类")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopCategoryService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('shop:shopCategory:save')")
    @OperationLog
    @ApiOperation("批量添加商品分类")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopCategory> list) {
        if (shopCategoryService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopCategory:update')")
    @OperationLog
    @ApiOperation("批量修改商品分类")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopCategory> batchParam) {
        if (batchParam.update(shopCategoryService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopCategory:remove')")
    @OperationLog
    @ApiOperation("批量删除商品分类")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopCategoryService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
