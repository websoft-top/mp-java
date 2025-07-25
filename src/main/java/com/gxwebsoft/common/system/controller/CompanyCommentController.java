package com.gxwebsoft.common.system.controller;

import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyComment;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.param.CompanyCommentParam;
import com.gxwebsoft.common.system.service.CompanyCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用评论控制器
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
@Api(tags = "应用评论管理")
@RestController
@RequestMapping("/api/system/company-comment")
public class CompanyCommentController extends BaseController {
    @Resource
    private CompanyCommentService companyCommentService;

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("分页查询应用评论")
    @GetMapping("/page")
    public ApiResult<PageResult<CompanyComment>> page(CompanyCommentParam param) {
        // 使用关联查询
        return success(companyCommentService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("查询全部应用评论")
    @GetMapping()
    public ApiResult<List<CompanyComment>> list(CompanyCommentParam param) {
        // 使用关联查询
        return success(companyCommentService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("根据id查询应用评论")
    @GetMapping("/{id}")
    public ApiResult<CompanyComment> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(companyCommentService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:company:save')")
    @OperationLog
    @ApiOperation("添加应用评论")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CompanyComment companyComment) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          companyComment.setUserId(loginUser.getUserId());
        }
        if (companyCommentService.save(companyComment)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:company:update')")
    @OperationLog
    @ApiOperation("修改应用评论")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CompanyComment companyComment) {
        if (companyCommentService.updateById(companyComment)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:company:remove')")
    @OperationLog
    @ApiOperation("删除应用评论")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (companyCommentService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:company:save')")
    @OperationLog
    @ApiOperation("批量添加应用评论")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CompanyComment> list) {
        if (companyCommentService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:company:update')")
    @OperationLog
    @ApiOperation("批量修改应用评论")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CompanyComment> batchParam) {
        if (batchParam.update(companyCommentService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:company:remove')")
    @OperationLog
    @ApiOperation("批量删除应用评论")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (companyCommentService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
