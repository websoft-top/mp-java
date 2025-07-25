package com.gxwebsoft.common.system.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.entity.UserReferee;
import com.gxwebsoft.common.system.param.UserRefereeParam;
import com.gxwebsoft.common.system.service.UserRefereeService;
import com.gxwebsoft.common.system.service.UserService;
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
 * @since 2023-10-07 22:56:36
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/api/system/user-referee")
public class UserRefereeController extends BaseController {
    @Resource
    private UserRefereeService userRefereeService;
    @Resource
    private UserService userService;

    @PreAuthorize("hasAuthority('sys:userReferee:list')")
    @OperationLog
    @ApiOperation("分页查询用户推荐关系表")
    @GetMapping("/page")
    public ApiResult<PageResult<UserReferee>> page(UserRefereeParam param) {
        // 使用关联查询
        return success(userRefereeService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:userReferee:list')")
    @OperationLog
    @ApiOperation("查询全部用户推荐关系表")
    @GetMapping()
    public ApiResult<List<UserReferee>> list(UserRefereeParam param) {
        // 使用关联查询
        return success(userRefereeService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:userReferee:list')")
    @OperationLog
    @ApiOperation("根据id查询用户推荐关系表")
    @GetMapping("/{id}")
    public ApiResult<UserReferee> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(userRefereeService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:userReferee:save')")
    @OperationLog
    @ApiOperation("添加用户推荐关系表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody UserReferee userReferee) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
            userReferee.setUserId(loginUser.getUserId());
        }
        if (userRefereeService.save(userReferee)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:userReferee:update')")
    @OperationLog
    @ApiOperation("修改用户推荐关系表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody UserReferee userReferee) {
        if (userRefereeService.updateById(userReferee)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:userReferee:remove')")
    @OperationLog
    @ApiOperation("删除用户推荐关系表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (userRefereeService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:userReferee:save')")
    @OperationLog
    @ApiOperation("批量添加用户推荐关系表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<UserReferee> list) {
        if (userRefereeService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:userReferee:update')")
    @OperationLog
    @ApiOperation("批量修改用户推荐关系表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<UserReferee> batchParam) {
        if (batchParam.update(userRefereeService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:userReferee:remove')")
    @OperationLog
    @ApiOperation("批量删除用户推荐关系表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (userRefereeService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("查询推荐人信息")
    @GetMapping("/getReferee/{id}")
    public ApiResult<User> getReferee(@PathVariable("id") Integer id) {
        if (id == null) {
            return fail("参数错误", null);
        }

        final UserReferee referee = userRefereeService.getOne(new LambdaQueryWrapper<UserReferee>()
                .eq(UserReferee::getUserId, id)
                .eq(UserReferee::getDeleted, 0));

        if (ObjectUtil.isEmpty(referee)) {
            return fail("查询失败", null);
        }

        final User user = userService.getByIdRel(referee.getDealerId());
        if (ObjectUtil.isNotEmpty(user)) {
            return success(user);
        }
        return fail("查询失败", null);
    }

    @ApiOperation("查询推荐人列表")
    @GetMapping("/getRefereeList/{id}")
    public ApiResult<List<User>> getRefereeList(@PathVariable("id") Integer id) {
        if (id == null) {
            return fail("参数错误", null);
        }

        final List<UserReferee> refereeList = userRefereeService.list(new LambdaQueryWrapper<UserReferee>()
                .eq(UserReferee::getDealerId, id)
                .eq(UserReferee::getDeleted, 0));

        if (ObjectUtil.isEmpty(refereeList)) {
            return fail("查询失败", null);
        }

        final List<User> users = userService.list(
                new LambdaQueryWrapper<User>()
                        .in(User::getUserId, refereeList.stream().map(UserReferee::getUserId).toList())
        );
        if (ObjectUtil.isNotEmpty(users)) {
            return success(users);
        }
        return fail("查询失败", null);
    }

}
