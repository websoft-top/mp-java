package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsMpPagesService;
import com.gxwebsoft.cms.entity.CmsMpPages;
import com.gxwebsoft.cms.param.CmsMpPagesParam;
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
 * 小程序页面控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "小程序页面管理")
@RestController
@RequestMapping("/api/cms/cms-mp-pages")
public class CmsMpPagesController extends BaseController {
    @Resource
    private CmsMpPagesService cmsMpPagesService;

    @ApiOperation("分页查询小程序页面")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsMpPages>> page(CmsMpPagesParam param) {
        // 使用关联查询
        return success(cmsMpPagesService.pageRel(param));
    }

    @ApiOperation("查询全部小程序页面")
    @GetMapping()
    public ApiResult<List<CmsMpPages>> list(CmsMpPagesParam param) {
        // 使用关联查询
        return success(cmsMpPagesService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsMpPages:list')")
    @OperationLog
    @ApiOperation("根据id查询小程序页面")
    @GetMapping("/{id}")
    public ApiResult<CmsMpPages> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsMpPagesService.getByIdRel(id));
    }

    @ApiOperation("添加小程序页面")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsMpPages cmsMpPages) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsMpPages.setUserId(loginUser.getUserId());
        }
        if (cmsMpPagesService.save(cmsMpPages)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改小程序页面")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsMpPages cmsMpPages) {
        if (cmsMpPagesService.updateById(cmsMpPages)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除小程序页面")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsMpPagesService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加小程序页面")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsMpPages> list) {
        if (cmsMpPagesService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改小程序页面")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsMpPages> batchParam) {
        if (batchParam.update(cmsMpPagesService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除小程序页面")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsMpPagesService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
