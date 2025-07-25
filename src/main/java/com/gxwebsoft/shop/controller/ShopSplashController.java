package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopSplashService;
import com.gxwebsoft.shop.entity.ShopSplash;
import com.gxwebsoft.shop.param.ShopSplashParam;
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
 * 开屏广告控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
@Api(tags = "开屏广告管理")
@RestController
@RequestMapping("/api/shop/shop-splash")
public class ShopSplashController extends BaseController {
    @Resource
    private ShopSplashService shopSplashService;

    @ApiOperation("分页查询开屏广告")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopSplash>> page(ShopSplashParam param) {
        // 使用关联查询
        return success(shopSplashService.pageRel(param));
    }

    @ApiOperation("查询全部开屏广告")
    @GetMapping()
    public ApiResult<List<ShopSplash>> list(ShopSplashParam param) {
        // 使用关联查询
        return success(shopSplashService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopSplash:list')")
    @ApiOperation("根据id查询开屏广告")
    @GetMapping("/{id}")
    public ApiResult<ShopSplash> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopSplashService.getByIdRel(id));
    }

    @ApiOperation("添加开屏广告")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopSplash shopSplash) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopSplash.setUserId(loginUser.getUserId());
        }
        if (shopSplashService.save(shopSplash)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改开屏广告")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopSplash shopSplash) {
        if (shopSplashService.updateById(shopSplash)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除开屏广告")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopSplashService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加开屏广告")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopSplash> list) {
        if (shopSplashService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改开屏广告")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopSplash> batchParam) {
        if (batchParam.update(shopSplashService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除开屏广告")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopSplashService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
