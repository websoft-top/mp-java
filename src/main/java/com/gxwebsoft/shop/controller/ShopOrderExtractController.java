package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopOrderExtractService;
import com.gxwebsoft.shop.entity.ShopOrderExtract;
import com.gxwebsoft.shop.param.ShopOrderExtractParam;
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
 * 自提订单联系方式控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "自提订单联系方式管理")
@RestController
@RequestMapping("/api/shop/shop-order-extract")
public class ShopOrderExtractController extends BaseController {
    @Resource
    private ShopOrderExtractService shopOrderExtractService;

    @ApiOperation("分页查询自提订单联系方式")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopOrderExtract>> page(ShopOrderExtractParam param) {
        // 使用关联查询
        return success(shopOrderExtractService.pageRel(param));
    }

    @ApiOperation("查询全部自提订单联系方式")
    @GetMapping()
    public ApiResult<List<ShopOrderExtract>> list(ShopOrderExtractParam param) {
        // 使用关联查询
        return success(shopOrderExtractService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopOrderExtract:list')")
    @ApiOperation("根据id查询自提订单联系方式")
    @GetMapping("/{id}")
    public ApiResult<ShopOrderExtract> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopOrderExtractService.getByIdRel(id));
    }

    @ApiOperation("添加自提订单联系方式")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopOrderExtract shopOrderExtract) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopOrderExtract.setUserId(loginUser.getUserId());
        }
        if (shopOrderExtractService.save(shopOrderExtract)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改自提订单联系方式")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopOrderExtract shopOrderExtract) {
        if (shopOrderExtractService.updateById(shopOrderExtract)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除自提订单联系方式")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopOrderExtractService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加自提订单联系方式")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopOrderExtract> list) {
        if (shopOrderExtractService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改自提订单联系方式")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopOrderExtract> batchParam) {
        if (batchParam.update(shopOrderExtractService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除自提订单联系方式")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopOrderExtractService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
