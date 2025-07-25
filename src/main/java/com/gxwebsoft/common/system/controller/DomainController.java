package com.gxwebsoft.common.system.controller;

import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Domain;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.param.DomainParam;
import com.gxwebsoft.common.system.service.DomainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 授权域名控制器
 *
 * @author 科技小王子
 * @since 2024-09-19 23:56:33
 */
@Api(tags = "授权域名管理")
@RestController
@RequestMapping("/api/system/domain")
public class DomainController extends BaseController {
    @Resource
    private DomainService domainService;

    @ApiOperation("分页查询授权域名")
    @GetMapping("/page")
    public ApiResult<PageResult<Domain>> page(DomainParam param) {
        // 使用关联查询
        return success(domainService.pageRel(param));
    }

    @ApiOperation("查询全部授权域名")
    @GetMapping()
    public ApiResult<List<Domain>> list(DomainParam param) {
        // 使用关联查询
        return success(domainService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:domain:list')")
    @OperationLog
    @ApiOperation("根据id查询授权域名")
    @GetMapping("/{id}")
    public ApiResult<Domain> get(@PathVariable("id") Integer id) {
        // 使用关联查询
        return success(domainService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:domain:save')")
    @OperationLog
    @ApiOperation("添加授权域名")
    @PostMapping()
    public ApiResult<?> save(@RequestBody Domain domain) {
        // 记录当前登录用户id
        User loginUser = getLoginUser();
        if (loginUser != null) {
          domain.setUserId(loginUser.getUserId());
        }
        if (domainService.save(domain)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:domain:update')")
    @OperationLog
    @ApiOperation("修改授权域名")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Domain domain) {
        if (domainService.updateById(domain)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:domain:remove')")
    @OperationLog
    @ApiOperation("删除授权域名")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (domainService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:domain:save')")
    @OperationLog
    @ApiOperation("批量添加授权域名")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<Domain> list) {
        if (domainService.saveBatch(list)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:domain:update')")
    @OperationLog
    @ApiOperation("批量修改授权域名")
    @PutMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody BatchParam<Domain> batchParam) {
        if (batchParam.update(domainService, "id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:domain:remove')")
    @OperationLog
    @ApiOperation("批量删除授权域名")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (domainService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
