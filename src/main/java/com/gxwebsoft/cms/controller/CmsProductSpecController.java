package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsProductSpecService;
import com.gxwebsoft.cms.entity.CmsProductSpec;
import com.gxwebsoft.cms.param.CmsProductSpecParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.system.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规格控制器
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Api(tags = "规格管理")
@RestController
@RequestMapping("/api/cms/cms-product-spec")
public class CmsProductSpecController extends BaseController {
    @Resource
    private CmsProductSpecService cmsProductSpecService;

    @ApiOperation("分页查询规格")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsProductSpec>> page(CmsProductSpecParam param) {
        // 使用关联查询
        return success(cmsProductSpecService.pageRel(param));
    }

    @ApiOperation("查询全部规格")
    @GetMapping()
    public ApiResult<List<CmsProductSpec>> list(CmsProductSpecParam param) {
        // 使用关联查询
        return success(cmsProductSpecService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsProductSpec:list')")
    @OperationLog
    @ApiOperation("根据id查询规格")
    @GetMapping("/{id}")
    public ApiResult<CmsProductSpec> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsProductSpecService.getByIdRel(id));
    }

    @ApiOperation("添加规格")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsProductSpec cmsProductSpec) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsProductSpec.setUserId(loginUser.getUserId());
        }
        if (cmsProductSpecService.save(cmsProductSpec)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改规格")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsProductSpec cmsProductSpec) {
        if (cmsProductSpecService.updateById(cmsProductSpec)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除规格")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsProductSpecService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加规格")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsProductSpec> list) {
        if (cmsProductSpecService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改规格")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsProductSpec> batchParam) {
        if (batchParam.update(cmsProductSpecService, "spec_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除规格")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsProductSpecService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
