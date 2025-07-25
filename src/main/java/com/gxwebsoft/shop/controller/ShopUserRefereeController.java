package com.gxwebsoft.shop.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.shop.service.ShopUserRefereeService;
import com.gxwebsoft.shop.entity.ShopUserReferee;
import com.gxwebsoft.shop.param.ShopUserRefereeParam;
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
 * 用户推荐关系表控制器
 *
 * @author 科技小王子
 * @since 2025-03-05 17:05:28
 */
@Api(tags = "用户推荐关系表管理")
@RestController
@RequestMapping("/api/shop/shop-user-referee")
public class ShopUserRefereeController extends BaseController {
    @Resource
    private ShopUserRefereeService shopUserRefereeService;

    @ApiOperation("分页查询用户推荐关系表")
    @GetMapping("/page")
    public ApiResult<PageResult<ShopUserReferee>> page(ShopUserRefereeParam param) {
        // 使用关联查询
        return success(shopUserRefereeService.pageRel(param));
    }

    @ApiOperation("查询全部用户推荐关系表")
    @GetMapping()
    public ApiResult<List<ShopUserReferee>> list(ShopUserRefereeParam param) {
        // 使用关联查询
        return success(shopUserRefereeService.listRel(param));
    }

    @PreAuthorize("hasAuthority('shop:shopUserReferee:list')")
    @ApiOperation("根据id查询用户推荐关系表")
    @GetMapping("/{id}")
    public ApiResult<ShopUserReferee> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(shopUserRefereeService.getByIdRel(id));
    }

    @ApiOperation("添加用户推荐关系表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody ShopUserReferee shopUserReferee) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         shopUserReferee.setUserId(loginUser.getUserId());
        }
        if (shopUserRefereeService.save(shopUserReferee)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改用户推荐关系表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody ShopUserReferee shopUserReferee) {
        if (shopUserRefereeService.updateById(shopUserReferee)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除用户推荐关系表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (shopUserRefereeService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加用户推荐关系表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<ShopUserReferee> list) {
        if (shopUserRefereeService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改用户推荐关系表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopUserReferee> batchParam) {
        if (batchParam.update(shopUserRefereeService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除用户推荐关系表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (shopUserRefereeService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
