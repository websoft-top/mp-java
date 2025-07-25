package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsTemplateService;
import com.gxwebsoft.cms.entity.CmsTemplate;
import com.gxwebsoft.cms.param.CmsTemplateParam;
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
 * 网站模版控制器
 *
 * @author 科技小王子
 * @since 2025-01-21 14:21:16
 */
@Api(tags = "网站模版管理")
@RestController
@RequestMapping("/api/cms/cms-template")
public class CmsTemplateController extends BaseController {
    @Resource
    private CmsTemplateService cmsTemplateService;

    @ApiOperation("分页查询网站模版")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsTemplate>> page(CmsTemplateParam param) {
        // 使用关联查询
        return success(cmsTemplateService.pageRel(param));
    }

    @ApiOperation("查询全部网站模版")
    @GetMapping()
    public ApiResult<List<CmsTemplate>> list(CmsTemplateParam param) {
        // 使用关联查询
        return success(cmsTemplateService.listRel(param));
    }

    @ApiOperation("根据id查询网站模版")
    @GetMapping("/{id}")
    public ApiResult<CmsTemplate> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsTemplateService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('cms:cmsTemplate:save')")
    @ApiOperation("添加网站模版")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsTemplate cmsTemplate) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
         cmsTemplate.setUserId(loginUser.getUserId());
        }
        if (cmsTemplateService.save(cmsTemplate)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsTemplate:update')")
    @ApiOperation("修改网站模版")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsTemplate cmsTemplate) {
        if (cmsTemplateService.updateById(cmsTemplate)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsTemplate:remove')")
    @ApiOperation("删除网站模版")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsTemplateService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsTemplate:save')")
    @ApiOperation("批量添加网站模版")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsTemplate> list) {
        if (cmsTemplateService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsTemplate:update')")
    @ApiOperation("批量修改网站模版")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsTemplate> batchParam) {
        if (batchParam.update(cmsTemplateService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsTemplate:remove')")
    @ApiOperation("批量删除网站模版")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsTemplateService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
