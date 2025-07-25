package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsProductUrlService;
import com.gxwebsoft.cms.entity.CmsProductUrl;
import com.gxwebsoft.cms.param.CmsProductUrlParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.annotation.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 域名控制器
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Api(tags = "域名管理")
@RestController
@RequestMapping("/api/cms/cms-product-url")
public class CmsProductUrlController extends BaseController {
    @Resource
    private CmsProductUrlService cmsProductUrlService;

    @ApiOperation("分页查询域名")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsProductUrl>> page(CmsProductUrlParam param) {
        // 使用关联查询
        return success(cmsProductUrlService.pageRel(param));
    }

    @ApiOperation("查询全部域名")
    @GetMapping()
    public ApiResult<List<CmsProductUrl>> list(CmsProductUrlParam param) {
        // 使用关联查询
        return success(cmsProductUrlService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsProductUrl:list')")
    @OperationLog
    @ApiOperation("根据id查询域名")
    @GetMapping("/{id}")
    public ApiResult<CmsProductUrl> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsProductUrlService.getByIdRel(id));
    }

    @ApiOperation("添加域名")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsProductUrl cmsProductUrl) {
        if (cmsProductUrlService.save(cmsProductUrl)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改域名")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsProductUrl cmsProductUrl) {
        if (cmsProductUrlService.updateById(cmsProductUrl)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除域名")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsProductUrlService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加域名")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsProductUrl> list) {
        if (cmsProductUrlService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改域名")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsProductUrl> batchParam) {
        if (batchParam.update(cmsProductUrlService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除域名")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsProductUrlService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
