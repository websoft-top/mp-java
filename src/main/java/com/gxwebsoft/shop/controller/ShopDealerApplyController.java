package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopDealerApplyService;
import com.gxwebsoft.shop.entity.ShopDealerApply;
import com.gxwebsoft.shop.param.ShopDealerApplyParam;
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
 * 分销商申请记录表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "分销商申请记录表管理")
@RestController
@RequestMapping("/api/shop/shop-dealer-apply")
public class ShopDealerApplyController extends BaseController {
    @Resource
    private ShopDealerApplyService shopDealerApplyService;

    @ApiOperation("分页查询分销商申请记录表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopDealerApply>> page(ShopDealerApplyParam param) {
        // 使用关联查询
        return success(shopDealerApplyService.pageRel(param));
    }

    @ApiOperation("查询全部分销商申请记录表")
    @GetMapping()
    public ApiResult<List<ShopDealerApply>> list(ShopDealerApplyParam param) {
        // 使用关联查询
        return success(shopDealerApplyService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopDealerApply:list')")
    @ApiOperation("根据id查询分销商申请记录表")
    @GetMapping("/{id}")
    public ApiResult<ShopDealerApply> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopDealerApplyService.getByIdRel(id));
    }

    @ApiOperation("添加分销商申请记录表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopDealerApply shopDealerApply) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopDealerApply.setUserId(loginUser.getUserId());
        }
        if (shopDealerApplyService.save(shopDealerApply)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改分销商申请记录表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopDealerApply shopDealerApply) {
        if (shopDealerApplyService.updateById(shopDealerApply)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除分销商申请记录表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopDealerApplyService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加分销商申请记录表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopDealerApply> list) {
        if (shopDealerApplyService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改分销商申请记录表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopDealerApply> batchParam) {
        if (batchParam.update(shopDealerApplyService, "apply_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除分销商申请记录表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopDealerApplyService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
