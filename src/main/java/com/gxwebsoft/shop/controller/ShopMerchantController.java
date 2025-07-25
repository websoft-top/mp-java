package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopMerchantService;
import com.gxwebsoft.shop.entity.ShopMerchant;
import com.gxwebsoft.shop.param.ShopMerchantParam;
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
 * 商户控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "商户管理")
@RestController
@RequestMapping("/api/shop/shop-merchant")
public class ShopMerchantController extends BaseController {
    @Resource
    private ShopMerchantService shopMerchantService;

    @ApiOperation("分页查询商户")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopMerchant>> page(ShopMerchantParam param) {
        // 使用关联查询
        return success(shopMerchantService.pageRel(param));
    }

    @ApiOperation("查询全部商户")
    @GetMapping()
    public ApiResult<List<ShopMerchant>> list(ShopMerchantParam param) {
        // 使用关联查询
        return success(shopMerchantService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopMerchant:list')")
    @ApiOperation("根据id查询商户")
    @GetMapping("/{id}")
    public ApiResult<ShopMerchant> get(@PathVariable("id") Long id) {
        // 使用关联查询
        return success(shopMerchantService.getByIdRel(id));
    }

    @ApiOperation("添加商户")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopMerchant shopMerchant) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopMerchant.setUserId(loginUser.getUserId());
        }
        if (shopMerchantService.save(shopMerchant)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改商户")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopMerchant shopMerchant) {
        if (shopMerchantService.updateById(shopMerchant)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除商户")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopMerchantService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加商户")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopMerchant> list) {
        if (shopMerchantService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改商户")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopMerchant> batchParam) {
        if (batchParam.update(shopMerchantService, "merchant_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除商户")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopMerchantService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
