package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Tenant;
import com.gxwebsoft.common.system.param.TenantParam;

import java.util.List;

/**
 * 租户Service
 *
 * @author 科技小王子
 * @since 2023-07-17 17:49:53
 */
public interface TenantService extends IService<Tenant> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<Tenant>
     */
    PageResult<Tenant> pageRel(TenantParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<Tenant>
     */
    List<Tenant> listRel(TenantParam param);

    /**
     * 根据id查询
     *
     * @param tenantId 租户id
     * @return Tenant
     */
    Tenant getByIdRel(Integer tenantId);

}
