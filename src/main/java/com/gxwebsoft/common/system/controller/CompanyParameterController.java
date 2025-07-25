package com.gxwebsoft.common.system.controller;

import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyParameter;
import com.gxwebsoft.common.system.param.CompanyParameterParam;
import com.gxwebsoft.common.system.service.CompanyParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用参数控制器
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
@Api(tags = "应用参数管理")
@RestController
@RequestMapping("/api/system/company-parameter")
public class CompanyParameterController extends BaseController {
    @Resource
    private CompanyParameterService companyParameterService;

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("分页查询应用参数")
    @GetMapping("/page")
    public ApiResult<PageResult<CompanyParameter>> page(CompanyParameterParam param) {
        // 使用关联查询
        return success(companyParameterService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("查询全部应用参数")
    @GetMapping()
    public ApiResult<List<CompanyParameter>> list(CompanyParameterParam param) {
        // 使用关联查询
        return success(companyParameterService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("根据id查询应用参数")
    @GetMapping("/{id}")
    public ApiResult<CompanyParameter> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(companyParameterService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:company:save')")
    @OperationLog
    @ApiOperation("添加应用参数")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CompanyParameter companyParameter) {
        if (companyParameterService.save(companyParameter)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:company:update')")
    @OperationLog
    @ApiOperation("修改应用参数")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CompanyParameter companyParameter) {
        if (companyParameterService.updateById(companyParameter)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:company:remove')")
    @OperationLog
    @ApiOperation("删除应用参数")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (companyParameterService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:company:save')")
    @OperationLog
    @ApiOperation("批量添加应用参数")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CompanyParameter> list) {
        if (companyParameterService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:company:update')")
    @OperationLog
    @ApiOperation("批量修改应用参数")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CompanyParameter> batchParam) {
        if (batchParam.update(companyParameterService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:company:remove')")
    @OperationLog
    @ApiOperation("批量删除应用参数")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (companyParameterService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
