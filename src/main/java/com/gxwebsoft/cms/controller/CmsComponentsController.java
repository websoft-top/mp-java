package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsComponentsService;
import com.gxwebsoft.cms.entity.CmsComponents;
import com.gxwebsoft.cms.param.CmsComponentsParam;
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
 * 组件控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "组件管理")
@RestController
@RequestMapping("/api/cms/cms-components")
public class CmsComponentsController extends BaseController {
    @Resource
    private CmsComponentsService cmsComponentsService;

    @ApiOperation("分页查询组件")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsComponents>> page(CmsComponentsParam param) {
        // 使用关联查询
        return success(cmsComponentsService.pageRel(param));
    }

    @ApiOperation("查询全部组件")
    @GetMapping()
    public ApiResult<List<CmsComponents>> list(CmsComponentsParam param) {
        PageParam<CmsComponents, CmsComponentsParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(cmsComponentsService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(cmsComponentsService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsComponents:list')")
    @OperationLog
    @ApiOperation("根据id查询组件")
    @GetMapping("/{id}")
    public ApiResult<CmsComponents> get(@PathVariable("id") Integer id) {
        return success(cmsComponentsService.getById(id));
        // 使用关联查询
        //return success(cmsComponentsService.getByIdRel(id));
    }

    @ApiOperation("添加组件")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsComponents cmsComponents) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsComponents.setUserId(loginUser.getUserId());
        }
        if (cmsComponentsService.save(cmsComponents)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改组件")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsComponents cmsComponents) {
        if (cmsComponentsService.updateById(cmsComponents)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除组件")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsComponentsService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加组件")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsComponents> list) {
        if (cmsComponentsService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改组件")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsComponents> batchParam) {
        if (batchParam.update(cmsComponentsService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除组件")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsComponentsService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
