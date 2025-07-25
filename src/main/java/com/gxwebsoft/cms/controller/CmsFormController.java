package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsFormService;
import com.gxwebsoft.cms.entity.CmsForm;
import com.gxwebsoft.cms.param.CmsFormParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.PageParam;
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
 * 表单设计表控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "表单设计表管理")
@RestController
@RequestMapping("/api/cms/cms-form")
public class CmsFormController extends BaseController {
    @Resource
    private CmsFormService cmsFormService;

    @ApiOperation("分页查询表单设计表")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsForm>> page(CmsFormParam param) {
        // 使用关联查询
        return success(cmsFormService.pageRel(param));
    }

    @ApiOperation("查询全部表单设计表")
    @GetMapping()
    public ApiResult<List<CmsForm>> list(CmsFormParam param) {
        PageParam<CmsForm, CmsFormParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(cmsFormService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(cmsFormService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsForm:list')")
    @OperationLog
    @ApiOperation("根据id查询表单设计表")
    @GetMapping("/{id}")
    public ApiResult<CmsForm> get(@PathVariable("id") Integer id) {
        return success(cmsFormService.getById(id));
        // 使用关联查询
        //return success(cmsFormService.getByIdRel(id));
    }

    @ApiOperation("添加表单设计表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsForm cmsForm) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsForm.setUserId(loginUser.getUserId());
        }
        if (cmsFormService.save(cmsForm)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改表单设计表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsForm cmsForm) {
        if (cmsFormService.updateById(cmsForm)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除表单设计表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsFormService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加表单设计表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsForm> list) {
        if (cmsFormService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改表单设计表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsForm> batchParam) {
        if (batchParam.update(cmsFormService, "form_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除表单设计表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsFormService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
