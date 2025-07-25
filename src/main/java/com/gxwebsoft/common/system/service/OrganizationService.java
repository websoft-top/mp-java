package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Organization;
import com.gxwebsoft.common.system.param.OrganizationParam;

import java.util.List;

/**
 * 组织机构Service
 *
 * @author WebSoft
 * @since 2020-03-14 11:29:04
 */
public interface OrganizationService extends IService<Organization> {

    /**
     * 关联分页查询
     *
     * @param param 查询参数
     * @return PageResult<Organization>
     */
    PageResult<Organization> pageRel(OrganizationParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<Organization>
     */
    List<Organization> listRel(OrganizationParam param);

    /**
     * 根据id查询
     *
     * @param organizationId 机构id
     * @return Organization
     */
    Organization getByIdRel(Integer organizationId);

}
