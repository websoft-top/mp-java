package com.gxwebsoft.common.system.controller;

import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyContent;
import com.gxwebsoft.common.system.param.CompanyContentParam;
import com.gxwebsoft.common.system.service.CompanyContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用详情控制器
 *
 * @author 科技小王子
 * @since 2024-10-16 13:41:21
 */
@Api(tags = "应用详情管理")
@RestController
@RequestMapping("/api/system/company-content")
public class CompanyContentController extends BaseController {
    @Resource
    private CompanyContentService companyContentService;

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("分页查询应用详情")
    @GetMapping("/page")
    public ApiResult<PageResult<CompanyContent>> page(CompanyContentParam param) {
        // 使用关联查询
        return success(companyContentService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("查询全部应用详情")
    @GetMapping()
    public ApiResult<List<CompanyContent>> list(CompanyContentParam param) {
        // 使用关联查询
        return success(companyContentService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("根据id查询应用详情")
    @GetMapping("/{id}")
    public ApiResult<CompanyContent> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(companyContentService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:company:save')")
    @OperationLog
    @ApiOperation("添加应用详情")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CompanyContent companyContent) {
        if (companyContentService.save(companyContent)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:company:update')")
    @OperationLog
    @ApiOperation("修改应用详情")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CompanyContent companyContent) {
        if (companyContentService.updateById(companyContent)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:company:remove')")
    @OperationLog
    @ApiOperation("删除应用详情")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (companyContentService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:company:save')")
    @OperationLog
    @ApiOperation("批量添加应用详情")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CompanyContent> list) {
        if (companyContentService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:company:update')")
    @OperationLog
    @ApiOperation("批量修改应用详情")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CompanyContent> batchParam) {
        if (batchParam.update(companyContentService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:company:remove')")
    @OperationLog
    @ApiOperation("批量删除应用详情")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (companyContentService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
