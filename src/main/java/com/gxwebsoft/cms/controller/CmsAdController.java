package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsAdService;
import com.gxwebsoft.cms.entity.CmsAd;
import com.gxwebsoft.cms.param.CmsAdParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.system.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 广告位控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "广告位管理")
@RestController
@RequestMapping("/api/cms/cms-ad")
public class CmsAdController extends BaseController {
    @Resource
    private CmsAdService cmsAdService;

    @ApiOperation("分页查询广告位")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsAd>> page(CmsAdParam param) {
        // 使用关联查询
        return success(cmsAdService.pageRel(param));
    }

    @ApiOperation("查询全部广告位")
    @GetMapping()
    public ApiResult<List<CmsAd>> list(CmsAdParam param) {
        // 使用关联查询
        return success(cmsAdService.listRel(param));
    }

    @ApiOperation("根据id查询广告位")
    @GetMapping("/{id}")
    public ApiResult<CmsAd> get(@PathVariable("id") Integer id) {
        // 使用关联查询
      final CmsAd ad = cmsAdService.getByIdRel(id);
      return success(ad);
    }

    @ApiOperation("添加广告位")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsAd cmsAd) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsAd.setUserId(loginUser.getUserId());
        }
        if (cmsAdService.save(cmsAd)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改广告位")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsAd cmsAd) {
        if (cmsAdService.updateById(cmsAd)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除广告位")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsAdService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加广告位")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsAd> list) {
        if (cmsAdService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改广告位")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsAd> batchParam) {
        if (batchParam.update(cmsAdService, "ad_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除广告位")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsAdService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
