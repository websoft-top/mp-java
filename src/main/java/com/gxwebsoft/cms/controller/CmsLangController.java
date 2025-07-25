package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsLangService;
import com.gxwebsoft.cms.entity.CmsLang;
import com.gxwebsoft.cms.param.CmsLangParam;
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
 * 国际化控制器
 *
 * @author 科技小王子
 * @since 2025-01-06 19:29:26
 */
@Api(tags = "国际化管理")
@RestController
@RequestMapping("/api/cms/cms-lang")
public class CmsLangController extends BaseController {
    @Resource
    private CmsLangService cmsLangService;

    @ApiOperation("分页查询国际化")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsLang>> page(CmsLangParam param) {
        // 使用关联查询
        return success(cmsLangService.pageRel(param));
    }

    @ApiOperation("查询全部国际化")
    @GetMapping()
    public ApiResult<List<CmsLang>> list(CmsLangParam param) {
        // 使用关联查询
        return success(cmsLangService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsLang:list')")
    @ApiOperation("根据id查询国际化")
    @GetMapping("/{id}")
    public ApiResult<CmsLang> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsLangService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('cms:cmsLang:save')")
    @ApiOperation("添加国际化")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsLang cmsLang) {
        if (cmsLangService.save(cmsLang)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsLang:update')")
    @ApiOperation("修改国际化")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsLang cmsLang) {
        if (cmsLangService.updateById(cmsLang)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsLang:remove')")
    @ApiOperation("删除国际化")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsLangService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsLang:save')")
    @ApiOperation("批量添加国际化")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsLang> list) {
        if (cmsLangService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsLang:update')")
    @ApiOperation("批量修改国际化")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsLang> batchParam) {
        if (batchParam.update(cmsLangService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsLang:reomve')")
    @ApiOperation("批量删除国际化")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsLangService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
