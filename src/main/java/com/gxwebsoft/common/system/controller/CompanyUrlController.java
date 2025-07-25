package com.gxwebsoft.common.system.controller;

import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyUrl;
import com.gxwebsoft.common.system.param.CompanyUrlParam;
import com.gxwebsoft.common.system.service.CompanyUrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用域名控制器
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
@Api(tags = "应用域名管理")
@RestController
@RequestMapping("/api/system/company-url")
public class CompanyUrlController extends BaseController {
    @Resource
    private CompanyUrlService companyUrlService;

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("分页查询应用域名")
    @GetMapping("/page")
    public ApiResult<PageResult<CompanyUrl>> page(CompanyUrlParam param) {
        // 使用关联查询
        return success(companyUrlService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("查询全部应用域名")
    @GetMapping()
    public ApiResult<List<CompanyUrl>> list(CompanyUrlParam param) {
        // 使用关联查询
        return success(companyUrlService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:company:list')")
    @OperationLog
    @ApiOperation("根据id查询应用域名")
    @GetMapping("/{id}")
    public ApiResult<CompanyUrl> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(companyUrlService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:company:save')")
    @OperationLog
    @ApiOperation("添加应用域名")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CompanyUrl companyUrl) {
        if (companyUrlService.save(companyUrl)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:company:update')")
    @OperationLog
    @ApiOperation("修改应用域名")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CompanyUrl companyUrl) {
        if (companyUrlService.updateById(companyUrl)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:company:remove')")
    @OperationLog
    @ApiOperation("删除应用域名")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (companyUrlService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:company:save')")
    @OperationLog
    @ApiOperation("批量添加应用域名")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CompanyUrl> list) {
        if (companyUrlService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:company:update')")
    @OperationLog
    @ApiOperation("批量修改应用域名")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CompanyUrl> batchParam) {
        if (batchParam.update(companyUrlService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:company:remove')")
    @OperationLog
    @ApiOperation("批量删除应用域名")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (companyUrlService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
