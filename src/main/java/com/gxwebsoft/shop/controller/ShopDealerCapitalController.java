package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopDealerCapitalService;
import com.gxwebsoft.shop.entity.ShopDealerCapital;
import com.gxwebsoft.shop.param.ShopDealerCapitalParam;
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
 * 分销商资金明细表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "分销商资金明细表管理")
@RestController
@RequestMapping("/api/shop/shop-dealer-capital")
public class ShopDealerCapitalController extends BaseController {
    @Resource
    private ShopDealerCapitalService shopDealerCapitalService;

    @ApiOperation("分页查询分销商资金明细表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopDealerCapital>> page(ShopDealerCapitalParam param) {
        // 使用关联查询
        return success(shopDealerCapitalService.pageRel(param));
    }

    @ApiOperation("查询全部分销商资金明细表")
    @GetMapping()
    public ApiResult<List<ShopDealerCapital>> list(ShopDealerCapitalParam param) {
        // 使用关联查询
        return success(shopDealerCapitalService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopDealerCapital:list')")
    @ApiOperation("根据id查询分销商资金明细表")
    @GetMapping("/{id}")
    public ApiResult<ShopDealerCapital> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopDealerCapitalService.getByIdRel(id));
    }

    @ApiOperation("添加分销商资金明细表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopDealerCapital shopDealerCapital) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopDealerCapital.setUserId(loginUser.getUserId());
        }
        if (shopDealerCapitalService.save(shopDealerCapital)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改分销商资金明细表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopDealerCapital shopDealerCapital) {
        if (shopDealerCapitalService.updateById(shopDealerCapital)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除分销商资金明细表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopDealerCapitalService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加分销商资金明细表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopDealerCapital> list) {
        if (shopDealerCapitalService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改分销商资金明细表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopDealerCapital> batchParam) {
        if (batchParam.update(shopDealerCapitalService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除分销商资金明细表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopDealerCapitalService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
