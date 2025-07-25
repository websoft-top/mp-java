package com.gxwebsoft.common.system.controller;

import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.OperationRecord;
import com.gxwebsoft.common.system.param.OperationRecordParam;
import com.gxwebsoft.common.system.service.OperationRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志控制器
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:12
 */
@Api(tags = "操作日志")
@RestController
@RequestMapping("/api/system/operation-record")
public class OperationRecordController extends BaseController {
    @Resource
    private OperationRecordService operationRecordService;

    /**
     * 分页查询操作日志
     */
    @PreAuthorize("hasAuthority('sys:operation-record:list')")
    @ApiOperation("分页查询操作日志")
    @GetMapping("/page")
    public ApiResult<PageResult<OperationRecord>> page(OperationRecordParam param) {
        return success(operationRecordService.pageRel(param));
    }

    /**
     * 查询全部操作日志
     */
    @PreAuthorize("hasAuthority('sys:operation-record:list')")
    @ApiOperation("查询全部操作日志")
    @GetMapping()
    public ApiResult<List<OperationRecord>> list(OperationRecordParam param) {
        return success(operationRecordService.listRel(param));
    }

    /**
     * 根据id查询操作日志
     */
    @PreAuthorize("hasAuthority('sys:operation-record:list')")
    @ApiOperation("根据id查询操作日志")
    @GetMapping("/{id}")
    public ApiResult<OperationRecord> get(@PathVariable("id") Integer id) {
        return success(operationRecordService.getByIdRel(id));
    }

}
