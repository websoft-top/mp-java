package com.gxwebsoft.cms.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsWebsiteSettingService;
import com.gxwebsoft.cms.entity.CmsWebsiteSetting;
import com.gxwebsoft.cms.param.CmsWebsiteSettingParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 网站设置控制器
 *
 * @author 科技小王子
 * @since 2025-02-19 01:35:44
 */
@Api(tags = "网站设置管理")
@RestController
@RequestMapping("/api/cms/cms-website-setting")
public class CmsWebsiteSettingController extends BaseController {
    @Resource
    private CmsWebsiteSettingService cmsWebsiteSettingService;

    @ApiOperation("分页查询网站设置")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsWebsiteSetting>> page(CmsWebsiteSettingParam param) {
        // 使用关联查询
        return success(cmsWebsiteSettingService.pageRel(param));
    }

    @ApiOperation("查询全部网站设置")
    @GetMapping()
    public ApiResult<List<CmsWebsiteSetting>> list(CmsWebsiteSettingParam param) {
        // 使用关联查询
        return success(cmsWebsiteSettingService.listRel(param));
    }

    @ApiOperation("根据id查询网站设置")
    @GetMapping("/{id}")
    public ApiResult<CmsWebsiteSetting> get(@PathVariable("id") Integer id) {
        // 使用关联查询
      final CmsWebsiteSetting cmsWebsiteSetting = cmsWebsiteSettingService.getOne(new LambdaQueryWrapper<CmsWebsiteSetting>().eq(CmsWebsiteSetting::getWebsiteId, id));
      if(ObjectUtil.isEmpty(cmsWebsiteSetting)){
        final CmsWebsiteSetting setting = new CmsWebsiteSetting();
        setting.setWebsiteId(id);
        cmsWebsiteSettingService.save(setting);
        return success(cmsWebsiteSettingService.getOne(new LambdaQueryWrapper<CmsWebsiteSetting>().eq(CmsWebsiteSetting::getWebsiteId, id)));
      }
      return success(cmsWebsiteSetting);
    }

    @PreAuthorize("hasAuthority('cms:cmsWebsiteSetting:save')")
    @ApiOperation("添加网站设置")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsWebsiteSetting cmsWebsiteSetting) {
        if (cmsWebsiteSettingService.save(cmsWebsiteSetting)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('cms:website:update')")
    @ApiOperation("修改网站设置")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsWebsiteSetting cmsWebsiteSetting) {
        if (cmsWebsiteSettingService.updateById(cmsWebsiteSetting)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsWebsiteSetting:remove')")
    @ApiOperation("删除网站设置")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsWebsiteSettingService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsWebsiteSetting:save')")
    @ApiOperation("批量添加网站设置")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsWebsiteSetting> list) {
        if (cmsWebsiteSettingService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsWebsiteSetting:update')")
    @ApiOperation("批量修改网站设置")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsWebsiteSetting> batchParam) {
        if (batchParam.update(cmsWebsiteSettingService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('cms:cmsWebsiteSetting:remove')")
    @ApiOperation("批量删除网站设置")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsWebsiteSettingService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
