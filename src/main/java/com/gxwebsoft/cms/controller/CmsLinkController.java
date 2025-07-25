package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsLinkService;
import com.gxwebsoft.cms.entity.CmsLink;
import com.gxwebsoft.cms.param.CmsLinkParam;
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
 * 常用链接控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "常用链接管理")
@RestController
@RequestMapping("/api/cms/cms-link")
public class CmsLinkController extends BaseController {
    @Resource
    private CmsLinkService cmsLinkService;

    @ApiOperation("分页查询常用链接")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsLink>> page(CmsLinkParam param) {
        // 使用关联查询
        return success(cmsLinkService.pageRel(param));
    }

    @ApiOperation("查询全部常用链接")
    @GetMapping()
    public ApiResult<List<CmsLink>> list(CmsLinkParam param) {
        // 使用关联查询
        return success(cmsLinkService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsLink:list')")
    @OperationLog
    @ApiOperation("根据id查询常用链接")
    @GetMapping("/{id}")
    public ApiResult<CmsLink> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsLinkService.getByIdRel(id));
    }

    @ApiOperation("添加常用链接")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsLink cmsLink) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsLink.setUserId(loginUser.getUserId());
        }
        if (cmsLinkService.save(cmsLink)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改常用链接")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsLink cmsLink) {
        if (cmsLinkService.updateById(cmsLink)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除常用链接")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsLinkService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加常用链接")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsLink> list) {
        if (cmsLinkService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改常用链接")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsLink> batchParam) {
        if (batchParam.update(cmsLinkService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除常用链接")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsLinkService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
