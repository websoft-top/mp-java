package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopDealerUserService;
import com.gxwebsoft.shop.entity.ShopDealerUser;
import com.gxwebsoft.shop.param.ShopDealerUserParam;
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
 * 分销商用户记录表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "分销商用户记录表管理")
@RestController
@RequestMapping("/api/shop/shop-dealer-user")
public class ShopDealerUserController extends BaseController {
    @Resource
    private ShopDealerUserService shopDealerUserService;

    @ApiOperation("分页查询分销商用户记录表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopDealerUser>> page(ShopDealerUserParam param) {
        // 使用关联查询
        return success(shopDealerUserService.pageRel(param));
    }

    @ApiOperation("查询全部分销商用户记录表")
    @GetMapping()
    public ApiResult<List<ShopDealerUser>> list(ShopDealerUserParam param) {
        // 使用关联查询
        return success(shopDealerUserService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopDealerUser:list')")
    @ApiOperation("根据id查询分销商用户记录表")
    @GetMapping("/{id}")
    public ApiResult<ShopDealerUser> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopDealerUserService.getByIdRel(id));
    }

    @ApiOperation("添加分销商用户记录表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopDealerUser shopDealerUser) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopDealerUser.setUserId(loginUser.getUserId());
        }
        if (shopDealerUserService.save(shopDealerUser)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改分销商用户记录表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopDealerUser shopDealerUser) {
        if (shopDealerUserService.updateById(shopDealerUser)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除分销商用户记录表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopDealerUserService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加分销商用户记录表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopDealerUser> list) {
        if (shopDealerUserService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改分销商用户记录表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopDealerUser> batchParam) {
        if (batchParam.update(shopDealerUserService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除分销商用户记录表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopDealerUserService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
