package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopDealerRefereeService;
import com.gxwebsoft.shop.entity.ShopDealerReferee;
import com.gxwebsoft.shop.param.ShopDealerRefereeParam;
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
 * 分销商推荐关系表控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "分销商推荐关系表管理")
@RestController
@RequestMapping("/api/shop/shop-dealer-referee")
public class ShopDealerRefereeController extends BaseController {
    @Resource
    private ShopDealerRefereeService shopDealerRefereeService;

    @ApiOperation("分页查询分销商推荐关系表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopDealerReferee>> page(ShopDealerRefereeParam param) {
        // 使用关联查询
        return success(shopDealerRefereeService.pageRel(param));
    }

    @ApiOperation("查询全部分销商推荐关系表")
    @GetMapping()
    public ApiResult<List<ShopDealerReferee>> list(ShopDealerRefereeParam param) {
        // 使用关联查询
        return success(shopDealerRefereeService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopDealerReferee:list')")
    @ApiOperation("根据id查询分销商推荐关系表")
    @GetMapping("/{id}")
    public ApiResult<ShopDealerReferee> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopDealerRefereeService.getByIdRel(id));
    }

    @ApiOperation("添加分销商推荐关系表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopDealerReferee shopDealerReferee) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopDealerReferee.setUserId(loginUser.getUserId());
        }
        if (shopDealerRefereeService.save(shopDealerReferee)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改分销商推荐关系表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopDealerReferee shopDealerReferee) {
        if (shopDealerRefereeService.updateById(shopDealerReferee)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除分销商推荐关系表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopDealerRefereeService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加分销商推荐关系表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopDealerReferee> list) {
        if (shopDealerRefereeService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改分销商推荐关系表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopDealerReferee> batchParam) {
        if (batchParam.update(shopDealerRefereeService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除分销商推荐关系表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopDealerRefereeService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
