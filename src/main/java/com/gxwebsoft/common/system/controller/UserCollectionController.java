package com.gxwebsoft.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.entity.UserCollection;
import com.gxwebsoft.common.system.param.UserCollectionParam;
import com.gxwebsoft.common.system.service.UserCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 我的收藏控制器
 *
 * @author 科技小王子
 * @since 2024-04-28 18:08:32
 */
@Api(tags = "用户收藏")
@RestController
@RequestMapping("/api/system/user-collection")
public class UserCollectionController extends BaseController {
    @Resource
    private UserCollectionService userCollectionService;

    @PreAuthorize("hasAuthority('sys:userCollection:list')")
    @ApiOperation("分页查询我的收藏")
    @GetMapping("/page")
    public ApiResult<PageResult<UserCollection>> page(UserCollectionParam param) {
        // 使用关联查询
        return success(userCollectionService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:userCollection:list')")
    @ApiOperation("查询全部我的收藏")
    @GetMapping()
    public ApiResult<List<UserCollection>> list(UserCollectionParam param) {
        // 使用关联查询
        return success(userCollectionService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:userCollection:list')")
    @ApiOperation("根据id查询我的收藏")
    @GetMapping("/{id}")
    public ApiResult<UserCollection> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(userCollectionService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:userCollection:save')")
    @OperationLog
    @ApiOperation("添加和取消收藏")
    @PostMapping()
    public ApiResult<?> save(@RequestBody UserCollection userCollection) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          userCollection.setUserId(loginUser.getUserId());
          userCollection.setTid(userCollection.getTid());
          final UserCollection one = userCollectionService.getOne(new LambdaQueryWrapper<UserCollection>().eq(UserCollection::getUserId, loginUser.getUserId()).eq(UserCollection::getTid, userCollection.getTid()).last("limit 1"));
          if (one != null) {
            userCollectionService.removeById(one.getId());
            return success("已取消收藏");
          }
          if (userCollectionService.save(userCollection)) {
            return success("已添加收藏");
          }
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:userCollection:update')")
    @OperationLog
    @ApiOperation("修改我的收藏")
    @PutMapping()
    public ApiResult<?> update(@RequestBody UserCollection userCollection) {
        if (userCollectionService.updateById(userCollection)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:userCollection:remove')")
    @OperationLog
    @ApiOperation("删除我的收藏")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (userCollectionService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:userCollection:save')")
    @OperationLog
    @ApiOperation("批量添加我的收藏")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<UserCollection> list) {
        if (userCollectionService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:userCollection:update')")
    @OperationLog
    @ApiOperation("批量修改我的收藏")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<UserCollection> batchParam) {
        if (batchParam.update(userCollectionService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:userCollection:remove')")
    @OperationLog
    @ApiOperation("批量删除我的收藏")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (userCollectionService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
