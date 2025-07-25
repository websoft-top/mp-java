package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsModelService;
import com.gxwebsoft.cms.entity.CmsModel;
import com.gxwebsoft.cms.param.CmsModelParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.system.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模型控制器
 *
 * @author 科技小王子
 * @since 2024-11-26 15:44:53
 */
@Api(tags = "模型管理")
@RestController
@RequestMapping("/api/cms/cms-model")
public class CmsModelController extends BaseController {
    @Resource
    private CmsModelService cmsModelService;

    @ApiOperation("分页查询模型")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsModel>> page(CmsModelParam param) {
        // 使用关联查询
        return success(cmsModelService.pageRel(param));
    }

    @ApiOperation("查询全部模型")
    @GetMapping()
    public ApiResult<List<CmsModel>> list(CmsModelParam param) {
        // 使用关联查询
        return success(cmsModelService.listRel(param));
    }

    @ApiOperation("根据id查询模型")
    @GetMapping("/{id}")
    public ApiResult<CmsModel> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsModelService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('cms:cmsModel:save')")
    @ApiOperation("添加模型")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsModel cmsModel) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         cmsModel.setUserId(loginUser.getUserId());
        }
        if (cmsModelService.save(cmsModel)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsModel:update')")
    @ApiOperation("修改模型")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsModel cmsModel) {
        if (cmsModelService.updateById(cmsModel)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsModel:remove')")
    @ApiOperation("删除模型")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsModelService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsModel:save')")
    @ApiOperation("批量添加模型")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsModel> list) {
        if (cmsModelService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsModel:update')")
    @ApiOperation("批量修改模型")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsModel> batchParam) {
        if (batchParam.update(cmsModelService, "model_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsModel:remvoe')")
    @ApiOperation("批量删除模型")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsModelService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
