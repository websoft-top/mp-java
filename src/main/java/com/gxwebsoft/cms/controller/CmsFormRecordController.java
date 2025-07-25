package com.gxwebsoft.cms.controller;

import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.cms.service.CmsFormRecordService;
import com.gxwebsoft.cms.entity.CmsFormRecord;
import com.gxwebsoft.cms.param.CmsFormRecordParam;
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
 * 表单数据记录表控制器
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Api(tags = "表单数据记录表管理")
@RestController
@RequestMapping("/api/cms/cms-form-record")
public class CmsFormRecordController extends BaseController {
    @Resource
    private CmsFormRecordService cmsFormRecordService;

    @ApiOperation("分页查询表单数据记录表")
    @GetMapping("/page")
    public ApiResult<PageResult<CmsFormRecord>> page(CmsFormRecordParam param) {
        // 使用关联查询
        return success(cmsFormRecordService.pageRel(param));
    }

    @ApiOperation("查询全部表单数据记录表")
    @GetMapping()
    public ApiResult<List<CmsFormRecord>> list(CmsFormRecordParam param) {
        PageParam<CmsFormRecord, CmsFormRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return success(cmsFormRecordService.list(page.getOrderWrapper()));
        // 使用关联查询
        //return success(cmsFormRecordService.listRel(param));
    }

    @PreAuthorize("hasAuthority('cms:cmsFormRecord:list')")
    @OperationLog
    @ApiOperation("根据id查询表单数据记录表")
    @GetMapping("/{id}")
    public ApiResult<CmsFormRecord> get(@PathVariable("id") Integer id) {
        return success(cmsFormRecordService.getById(id));
        // 使用关联查询
        //return success(cmsFormRecordService.getByIdRel(id));
    }

    @ApiOperation("添加表单数据记录表")
    @PostMapping()
    public ApiResult<?> save(@RequestBody CmsFormRecord cmsFormRecord) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          cmsFormRecord.setUserId(loginUser.getUserId());
        }
        if (cmsFormRecordService.save(cmsFormRecord)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("修改表单数据记录表")
    @PutMapping()
    public ApiResult<?> update(@RequestBody CmsFormRecord cmsFormRecord) {
        if (cmsFormRecordService.updateById(cmsFormRecord)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("删除表单数据记录表")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (cmsFormRecordService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @ApiOperation("批量添加表单数据记录表")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<CmsFormRecord> list) {
        if (cmsFormRecordService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @ApiOperation("批量修改表单数据记录表")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<CmsFormRecord> batchParam) {
        if (batchParam.update(cmsFormRecordService, "form_record_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("批量删除表单数据记录表")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (cmsFormRecordService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
