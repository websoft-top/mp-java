package com.gxwebsoft.common.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxwebsoft.common.core.annotation.OperationLog;
import com.gxwebsoft.common.core.web.*;
import com.gxwebsoft.common.system.entity.Organization;
import com.gxwebsoft.common.system.param.OrganizationParam;
import com.gxwebsoft.common.system.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 组织机构控制器
 *
 * @author WebSoft
 * @since 2020-03-14 11:29:04
 */
@Api(tags = "组织机构管理")
@RestController
@RequestMapping("/api/system/organization")
public class OrganizationController extends BaseController {
    @Resource
    private OrganizationService organizationService;

    @PreAuthorize("hasAuthority('sys:org:list')")
    @ApiOperation("分页查询组织机构")
    @GetMapping("/page")
    public ApiResult<PageResult<Organization>> page(OrganizationParam param) {
        return success(organizationService.pageRel(param));
    }

    @PreAuthorize("hasAuthority('sys:org:list')")
    @ApiOperation("查询全部组织机构")
    @GetMapping()
    public ApiResult<List<Organization>> list(OrganizationParam param) {
        return success(organizationService.listRel(param));
    }

    @PreAuthorize("hasAuthority('sys:org:list')")
    @ApiOperation("根据id查询组织机构")
    @GetMapping("/{id}")
    public ApiResult<Organization> get(@PathVariable("id") Integer id) {
        return success(organizationService.getByIdRel(id));
    }

    @PreAuthorize("hasAuthority('sys:org:save')")
    @ApiOperation("添加组织机构")
    @PostMapping()
    public ApiResult<?> add(@RequestBody Organization organization) {
        if (organization.getParentId() == null) {
            organization.setParentId(0);
        }
        if (organizationService.count(new LambdaQueryWrapper<Organization>()
                .eq(Organization::getOrganizationName, organization.getOrganizationName())
                .eq(Organization::getParentId, organization.getParentId())) > 0) {
            return fail("机构名称已存在");
        }
        if (organizationService.save(organization)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:org:update')")
    @ApiOperation("修改组织机构")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Organization organization) {
        if (organization.getOrganizationName() != null) {
            if (organization.getParentId() == null) {
                organization.setParentId(0);
            }
            if (organizationService.count(new LambdaQueryWrapper<Organization>()
                    .eq(Organization::getOrganizationName, organization.getOrganizationName())
                    .eq(Organization::getParentId, organization.getParentId())
                    .ne(Organization::getOrganizationId, organization.getOrganizationId())) > 0) {
                return fail("机构名称已存在");
            }
        }
        if (organizationService.updateById(organization)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:org:remove')")
    @ApiOperation("删除组织机构")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (organizationService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

    @PreAuthorize("hasAuthority('sys:org:save')")
    @ApiOperation("批量添加组织机构")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<Organization> organizationList) {
        if (organizationService.saveBatch(organizationList)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @PreAuthorize("hasAuthority('sys:org:update')")
    @ApiOperation("批量修改组织机构")
    @PutMapping("/batch")
    public ApiResult<?> updateBatch(@RequestBody BatchParam<Organization> batchParam) {
        if (batchParam.update(organizationService, Organization::getOrganizationId)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @PreAuthorize("hasAuthority('sys:org:remove')")
    @ApiOperation("批量删除组织机构")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (organizationService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
