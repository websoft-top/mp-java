package com.gxwebsoft.cms.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.gxwebsoft.cms.entity.CmsNavigation;
import com.gxwebsoft.cms.service.CmsNavigationService;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsDesignService;
import com.gxwebsoft.cms.entity.CmsDesign;
import com.gxwebsoft.cms.param.CmsDesignParam;
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
 * 页面管理记录表控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "页面管理记录表管理")
@RestController
@RequestMapping("/api/cms/cms-design")
public class CmsDesignController extends BaseController {
    @Resource
    private CmsDesignService cmsDesignService;
    @Resource
    private CmsNavigationService cmsNavigationService;

    @ApiOperation("分页查询页面管理记录表")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsDesign>> page(CmsDesignParam param) {
        // 使用关联查询
        return success(cmsDesignService.pageRel(param));
    }

    @ApiOperation("查询全部页面管理记录表")
    @GetMapping()
    public ApiResult<List<CmsDesign>> list(CmsDesignParam param) {
        // 使用关联查询
        return success(cmsDesignService.listRel(param));
    }

    @ApiOperation("根据id查询页面管理记录表")
    @GetMapping("/{id}")
    public ApiResult<CmsDesign> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(cmsDesignService.getByIdRel(id));
    }

    @ApiOperation("添加页面管理记录表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsDesign cmsDesign) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsDesign.setUserId(loginUser.getUserId());
        }
        if (cmsDesignService.save(cmsDesign)) {
          try {
            cmsNavigationService.update(new LambdaUpdateWrapper<CmsNavigation>().set(CmsNavigation::getBanner, cmsDesign.getPhoto()).eq(CmsNavigation::getNavigationId,cmsDesign.getCategoryId()));
            // 同步翻译英文版
            cmsDesignService.translate(cmsDesign);
            return success("添加成功");
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
        return fail("添加失败");
    }

    @ApiOperation("修改页面管理记录表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsDesign cmsDesign) {
        if (cmsDesignService.updateById(cmsDesign)) {
          // 同步翻译英文版
          cmsDesignService.translate(cmsDesign);
          return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除页面管理记录表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsDesignService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加页面管理记录表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsDesign> list) {
        if (cmsDesignService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改页面管理记录表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsDesign> batchParam) {
        if (batchParam.update(cmsDesignService, "page_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除页面管理记录表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsDesignService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
