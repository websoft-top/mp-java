package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopUsersService;
import com.gxwebsoft.shop.entity.ShopUsers;
import com.gxwebsoft.shop.param.ShopUsersParam;
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
 * 控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Api(tags = "管理")
@RestController
@RequestMapping("/api/shop/shop-users")
public class ShopUsersController extends BaseController {
    @Resource
    private ShopUsersService shopUsersService;

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopUsers>> page(ShopUsersParam param) {
        // 使用关联查询
        return success(shopUsersService.pageRel(param));
    }

    @ApiOperation("查询全部")
    @GetMapping()
    public ApiResult<List<ShopUsers>> list(ShopUsersParam param) {
        // 使用关联查询
        return success(shopUsersService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopUsers:list')")
    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public ApiResult<ShopUsers> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopUsersService.getByIdRel(id));
    }

    @ApiOperation("添加")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopUsers shopUsers) {
        if (shopUsersService.save(shopUsers)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopUsers shopUsers) {
        if (shopUsersService.updateById(shopUsers)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopUsersService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopUsers> list) {
        if (shopUsersService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopUsers> batchParam) {
        if (batchParam.update(shopUsersService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopUsersService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
