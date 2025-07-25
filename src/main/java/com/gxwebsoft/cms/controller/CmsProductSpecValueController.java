package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsProductSpecValueService;
import com.gxwebsoft.cms.entity.CmsProductSpecValue;
import com.gxwebsoft.cms.param.CmsProductSpecValueParam;
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
 * 规格值控制器
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Api(tags = "规格值管理")
@RestController
@RequestMapping("/api/cms/cms-product-spec-value")
public class CmsProductSpecValueController extends BaseController {
    @Resource
    private CmsProductSpecValueService cmsProductSpecValueService;

    @ApiOperation("分页查询规格值")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsProductSpecValue>> page(CmsProductSpecValueParam param) {
        // 使用关联查询
        return success(cmsProductSpecValueService.pageRel(param));
    }

    @ApiOperation("查询全部规格值")
    @GetMapping()
    public ApiResult<List<CmsProductSpecValue>> list(CmsProductSpecValueParam param) {
        // 使用关联查询
        return success(cmsProductSpecValueService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsProductSpecValue:list')")
    @OperationLog
    @ApiOperation("根据id查询规格值")
    @GetMapping("/{id}")
    public ApiResult<CmsProductSpecValue> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsProductSpecValueService.getByIdRel(id));
    }

    @ApiOperation("添加规格值")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsProductSpecValue cmsProductSpecValue) {
        if (cmsProductSpecValueService.save(cmsProductSpecValue)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改规格值")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsProductSpecValue cmsProductSpecValue) {
        if (cmsProductSpecValueService.updateById(cmsProductSpecValue)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除规格值")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsProductSpecValueService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加规格值")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsProductSpecValue> list) {
        if (cmsProductSpecValueService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改规格值")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsProductSpecValue> batchParam) {
        if (batchParam.update(cmsProductSpecValueService, "spec_value_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除规格值")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsProductSpecValueService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
