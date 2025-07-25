package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopSpecService;
import com.gxwebsoft.shop.entity.ShopSpec;
import com.gxwebsoft.shop.param.ShopSpecParam;
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
 * 规格控制器
 *
 * @author 科技小王子
 * @since 2025-05-01 09:44:00
 */
@Api(tags = "规格管理")
@RestController
@RequestMapping("/api/shop/shop-spec")
public class ShopSpecController extends BaseController {
    @Resource
    private ShopSpecService shopSpecService;

    @ApiOperation("分页查询规格")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopSpec>> page(ShopSpecParam param) {
        // 使用关联查询
        return success(shopSpecService.pageRel(param));
    }

    @ApiOperation("查询全部规格")
    @GetMapping()
    public ApiResult<List<ShopSpec>> list(ShopSpecParam param) {
        // 使用关联查询
        return success(shopSpecService.listRel(param));
    }

    @ApiOperation("根据id查询规格")
    @GetMapping("/{id}")
    public ApiResult<ShopSpec> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopSpecService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('shop:shopSpec:save')")
    @OperationLog
    @ApiOperation("添加规格")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopSpec shopSpec) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopSpec.setUserId(loginUser.getUserId());
        }
        if (shopSpecService.save(shopSpec)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopSpec:update')")
    @OperationLog
    @ApiOperation("修改规格")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopSpec shopSpec) {
        if (shopSpecService.updateById(shopSpec)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopSpec:remove')")
    @OperationLog
    @ApiOperation("删除规格")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopSpecService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('shop:shopSpec:save')")
    @OperationLog
    @ApiOperation("批量添加规格")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopSpec> list) {
        if (shopSpecService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('shop:shopSpec:update')")
    @OperationLog
    @ApiOperation("批量修改规格")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopSpec> batchParam) {
        if (batchParam.update(shopSpecService, "spec_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('shop:shopSpec:remove')")
    @OperationLog
    @ApiOperation("批量删除规格")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopSpecService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
