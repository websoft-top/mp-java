package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsLangLogService;
import com.gxwebsoft.cms.entity.CmsLangLog;
import com.gxwebsoft.cms.param.CmsLangLogParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 国际化记录启用控制器
 *
 * @author 科技小王子
 * @since 2025-01-06 19:29:26
 */
@Api(tags = "国际化记录启用管理")
@RestController
@RequestMapping("/api/cms/cms-lang-log")
public class CmsLangLogController extends BaseController {
    @Resource
    private CmsLangLogService cmsLangLogService;

    @ApiOperation("分页查询国际化记录启用")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsLangLog>> page(CmsLangLogParam param) {
        // 使用关联查询
        return success(cmsLangLogService.pageRel(param));
    }

    @ApiOperation("查询全部国际化记录启用")
    @GetMapping()
    public ApiResult<List<CmsLangLog>> list(CmsLangLogParam param) {
        // 使用关联查询
        return success(cmsLangLogService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsLangLog:list')")
    @ApiOperation("根据id查询国际化记录启用")
    @GetMapping("/{id}")
    public ApiResult<CmsLangLog> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsLangLogService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('cms:cmsLangLog:save')")
    @ApiOperation("添加国际化记录启用")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsLangLog cmsLangLog) {
        if (cmsLangLogService.save(cmsLangLog)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsLangLog:update')")
    @ApiOperation("修改国际化记录启用")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsLangLog cmsLangLog) {
        if (cmsLangLogService.updateById(cmsLangLog)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsLangLog:remove')")
    @ApiOperation("删除国际化记录启用")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsLangLogService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsLangLog:save')")
    @ApiOperation("批量添加国际化记录启用")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsLangLog> list) {
        if (cmsLangLogService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsLangLog:update')")
    @ApiOperation("批量修改国际化记录启用")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsLangLog> batchParam) {
        if (batchParam.update(cmsLangLogService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsLangLog:remove')")
    @ApiOperation("批量删除国际化记录启用")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsLangLogService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
