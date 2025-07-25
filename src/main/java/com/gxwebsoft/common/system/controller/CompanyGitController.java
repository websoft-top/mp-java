package com.gxwebsoft.common.system.controller;

import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyGit;
import com.gxwebsoft.common.system.param.CompanyGitParam;
import com.gxwebsoft.common.system.service.CompanyGitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 代码仓库控制器
 *
 * @author 科技小王子
 * @since 2024-10-19 18:08:51
 */
@Api(tags = "代码仓库管理")
@RestController
@RequestMapping("/api/system/company-git")
public class CompanyGitController extends BaseController {
    @Resource
    private CompanyGitService companyGitService;

    @PreAuthorize("hasAuthority('sys:companyGit:list')")
    @ApiOperation("分页查询代码仓库")
    @GetMapping("/page")
    public ApiResult<PageResult<CompanyGit>> page(CompanyGitParam param) {
        // 使用关联查询
        return success(companyGitService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:companyGit:list')")
    @ApiOperation("查询全部代码仓库")
    @GetMapping()
    public ApiResult<List<CompanyGit>> list(CompanyGitParam param) {
        // 使用关联查询
        return success(companyGitService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:companyGit:list')")
    @ApiOperation("根据id查询代码仓库")
    @GetMapping("/{id}")
    public ApiResult<CompanyGit> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(companyGitService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:companyGit:save')")
    @OperationLog
    @ApiOperation("添加代码仓库")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CompanyGit companyGit) {
        if (companyGitService.save(companyGit)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:companyGit:update')")
    @OperationLog
    @ApiOperation("修改代码仓库")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CompanyGit companyGit) {
        if (companyGitService.updateById(companyGit)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:companyGit:remove')")
    @OperationLog
    @ApiOperation("删除代码仓库")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (companyGitService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:companyGit:save')")
    @OperationLog
    @ApiOperation("批量添加代码仓库")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CompanyGit> list) {
        if (companyGitService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:companyGit:update')")
    @OperationLog
    @ApiOperation("批量修改代码仓库")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CompanyGit> batchParam) {
        if (batchParam.update(companyGitService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:companyGit:remove')")
    @OperationLog
    @ApiOperation("批量删除代码仓库")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (companyGitService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
