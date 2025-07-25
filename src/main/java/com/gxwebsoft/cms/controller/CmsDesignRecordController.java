package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsDesignRecordService;
import com.gxwebsoft.cms.entity.CmsDesignRecord;
import com.gxwebsoft.cms.param.CmsDesignRecordParam;
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
 * 页面组件表控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "页面组件表管理")
@RestController
@RequestMapping("/api/cms/cms-design-record")
public class CmsDesignRecordController extends BaseController {
    @Resource
    private CmsDesignRecordService cmsDesignRecordService;

    @ApiOperation("分页查询页面组件表")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsDesignRecord>> page(CmsDesignRecordParam param) {
        // 使用关联查询
        return success(cmsDesignRecordService.pageRel(param));
    }

    @ApiOperation("查询全部页面组件表")
    @GetMapping()
    public ApiResult<List<CmsDesignRecord>> list(CmsDesignRecordParam param) {
        PageParam<CmsDesignRecord, CmsDesignRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(cmsDesignRecordService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(cmsDesignRecordService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsDesignRecord:list')")
    @OperationLog
    @ApiOperation("根据id查询页面组件表")
    @GetMapping("/{id}")
    public ApiResult<CmsDesignRecord> get(@PathVariable("id") Integer id) {
        return success(cmsDesignRecordService.getById(id));
        // 使用关联查询
        //return success(cmsDesignRecordService.getByIdRel(id));
    }

    @ApiOperation("添加页面组件表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsDesignRecord cmsDesignRecord) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsDesignRecord.setUserId(loginUser.getUserId());
        }
        if (cmsDesignRecordService.save(cmsDesignRecord)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改页面组件表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsDesignRecord cmsDesignRecord) {
        if (cmsDesignRecordService.updateById(cmsDesignRecord)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除页面组件表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsDesignRecordService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加页面组件表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsDesignRecord> list) {
        if (cmsDesignRecordService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改页面组件表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsDesignRecord> batchParam) {
        if (batchParam.update(cmsDesignRecordService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除页面组件表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsDesignRecordService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
