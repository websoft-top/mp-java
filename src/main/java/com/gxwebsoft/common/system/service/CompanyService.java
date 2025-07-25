package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Company;
import com.gxwebsoft.common.system.param.CompanyParam;

import java.util.List;

/**
 * 企业信息Service
 *
 * @author 科技小王子
 * @since 2023-05-27 14:57:34
 */
public interface CompanyService extends IService<Company> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<Company>
     */
    PageResult<Company> pageRel(CompanyParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<Company>
     */
    List<Company> listRel(CompanyParam param);

    /**
     * 根据id查询
     *
     * @param companyId 企业id
     * @return Company
     */
    Company getByIdRel(Integer companyId);

    Company getByTenantIdRel(Integer tenantId);

    PageResult<Company> pageRelAll(CompanyParam param);

    void updateByCompanyId(Company company);

    boolean removeCompanyAll(Integer companyId);

    boolean undeleteAll(Integer companyId);
}
