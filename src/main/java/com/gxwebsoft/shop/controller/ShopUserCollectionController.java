package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopUserCollectionService;
import com.gxwebsoft.shop.entity.ShopUserCollection;
import com.gxwebsoft.shop.param.ShopUserCollectionParam;
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
 * 我的收藏控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Api(tags = "我的收藏管理")
@RestController
@RequestMapping("/api/shop/shop-user-collection")
public class ShopUserCollectionController extends BaseController {
    @Resource
    private ShopUserCollectionService shopUserCollectionService;

    @ApiOperation("分页查询我的收藏")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopUserCollection>> page(ShopUserCollectionParam param) {
        // 使用关联查询
        return success(shopUserCollectionService.pageRel(param));
    }

    @ApiOperation("查询全部我的收藏")
    @GetMapping()
    public ApiResult<List<ShopUserCollection>> list(ShopUserCollectionParam param) {
        // 使用关联查询
        return success(shopUserCollectionService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopUserCollection:list')")
    @ApiOperation("根据id查询我的收藏")
    @GetMapping("/{id}")
    public ApiResult<ShopUserCollection> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopUserCollectionService.getByIdRel(id));
    }

    @ApiOperation("添加我的收藏")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopUserCollection shopUserCollection) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopUserCollection.setUserId(loginUser.getUserId());
        }
        if (shopUserCollectionService.save(shopUserCollection)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改我的收藏")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopUserCollection shopUserCollection) {
        if (shopUserCollectionService.updateById(shopUserCollection)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除我的收藏")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopUserCollectionService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加我的收藏")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopUserCollection> list) {
        if (shopUserCollectionService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改我的收藏")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopUserCollection> batchParam) {
        if (batchParam.update(shopUserCollectionService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除我的收藏")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopUserCollectionService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
