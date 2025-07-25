package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopMerchantAccountService;
import com.gxwebsoft.shop.entity.ShopMerchantAccount;
import com.gxwebsoft.shop.param.ShopMerchantAccountParam;
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
 * 商户账号控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "商户账号管理")
@RestController
@RequestMapping("/api/shop/shop-merchant-account")
public class ShopMerchantAccountController extends BaseController {
    @Resource
    private ShopMerchantAccountService shopMerchantAccountService;

    @ApiOperation("分页查询商户账号")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopMerchantAccount>> page(ShopMerchantAccountParam param) {
        // 使用关联查询
        return success(shopMerchantAccountService.pageRel(param));
    }

    @ApiOperation("查询全部商户账号")
    @GetMapping()
    public ApiResult<List<ShopMerchantAccount>> list(ShopMerchantAccountParam param) {
        // 使用关联查询
        return success(shopMerchantAccountService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopMerchantAccount:list')")
    @ApiOperation("根据id查询商户账号")
    @GetMapping("/{id}")
    public ApiResult<ShopMerchantAccount> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopMerchantAccountService.getByIdRel(id));
    }

    @ApiOperation("添加商户账号")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopMerchantAccount shopMerchantAccount) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopMerchantAccount.setUserId(loginUser.getUserId());
        }
        if (shopMerchantAccountService.save(shopMerchantAccount)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改商户账号")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopMerchantAccount shopMerchantAccount) {
        if (shopMerchantAccountService.updateById(shopMerchantAccount)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除商户账号")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopMerchantAccountService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加商户账号")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopMerchantAccount> list) {
        if (shopMerchantAccountService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改商户账号")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopMerchantAccount> batchParam) {
        if (batchParam.update(shopMerchantAccountService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除商户账号")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopMerchantAccountService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
