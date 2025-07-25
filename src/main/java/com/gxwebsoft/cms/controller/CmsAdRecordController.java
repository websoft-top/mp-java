package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsAdRecordService;
import com.gxwebsoft.cms.entity.CmsAdRecord;
import com.gxwebsoft.cms.param.CmsAdRecordParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.annotation.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 广告图片控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "广告图片管理")
@RestController
@RequestMapping("/api/cms/cms-ad-record")
public class CmsAdRecordController extends BaseController {
    @Resource
    private CmsAdRecordService cmsAdRecordService;

    @ApiOperation("分页查询广告图片")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsAdRecord>> page(CmsAdRecordParam param) {
        // 使用关联查询
        return success(cmsAdRecordService.pageRel(param));
    }

    @ApiOperation("查询全部广告图片")
    @GetMapping()
    public ApiResult<List<CmsAdRecord>> list(CmsAdRecordParam param) {
        PageParam<CmsAdRecord, CmsAdRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(cmsAdRecordService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(cmsAdRecordService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsAdRecord:list')")
    @OperationLog
    @ApiOperation("根据id查询广告图片")
    @GetMapping("/{id}")
    public ApiResult<CmsAdRecord> get(@PathVariable("id") Integer id) {
        return success(cmsAdRecordService.getById(id));
        // 使用关联查询
        //return success(cmsAdRecordService.getByIdRel(id));
    }

    @ApiOperation("添加广告图片")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsAdRecord cmsAdRecord) {
        if (cmsAdRecordService.save(cmsAdRecord)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改广告图片")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsAdRecord cmsAdRecord) {
        if (cmsAdRecordService.updateById(cmsAdRecord)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除广告图片")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsAdRecordService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加广告图片")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsAdRecord> list) {
        if (cmsAdRecordService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改广告图片")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsAdRecord> batchParam) {
        if (batchParam.update(cmsAdRecordService, "ad_record_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除广告图片")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsAdRecordService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
