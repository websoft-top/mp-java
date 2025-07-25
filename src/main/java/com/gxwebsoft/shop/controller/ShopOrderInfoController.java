package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopOrderInfoService;
import com.gxwebsoft.shop.entity.ShopOrderInfo;
import com.gxwebsoft.shop.param.ShopOrderInfoParam;
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
 * 场地控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "场地管理")
@RestController
@RequestMapping("/api/shop/shop-order-info")
public class ShopOrderInfoController extends BaseController {
    @Resource
    private ShopOrderInfoService shopOrderInfoService;

    @ApiOperation("分页查询场地")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopOrderInfo>> page(ShopOrderInfoParam param) {
        // 使用关联查询
        return success(shopOrderInfoService.pageRel(param));
    }

    @ApiOperation("查询全部场地")
    @GetMapping()
    public ApiResult<List<ShopOrderInfo>> list(ShopOrderInfoParam param) {
        // 使用关联查询
        return success(shopOrderInfoService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopOrderInfo:list')")
    @ApiOperation("根据id查询场地")
    @GetMapping("/{id}")
    public ApiResult<ShopOrderInfo> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopOrderInfoService.getByIdRel(id));
    }

    @ApiOperation("添加场地")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopOrderInfo shopOrderInfo) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopOrderInfo.setUserId(loginUser.getUserId());
        }
        if (shopOrderInfoService.save(shopOrderInfo)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改场地")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopOrderInfo shopOrderInfo) {
        if (shopOrderInfoService.updateById(shopOrderInfo)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除场地")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopOrderInfoService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加场地")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopOrderInfo> list) {
        if (shopOrderInfoService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改场地")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopOrderInfo> batchParam) {
        if (batchParam.update(shopOrderInfoService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除场地")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopOrderInfoService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
