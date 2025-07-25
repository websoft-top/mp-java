package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.system.entity.Company;
import com.gxwebsoft.common.system.param.CompanyParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 企业信息Mapper
 *
 * @author 科技小王子
 * @since 2023-05-27 14:57:34
 */
public interface CompanyMapper extends BaseMapper<Company> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<Company>
     */
    List<Company> selectPageRel(@Param("page") IPage<Company> page,
                             @Param("param") CompanyParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<Company> selectListRel(@Param("param") CompanyParam param);

    @InterceptorIgnore(tenantLine = "true")
    List<Company> getCount(@Param("param") CompanyParam param);

    @InterceptorIgnore(tenantLine = "true")
    List<Company> selectPageRelAll(PageParam<Company, CompanyParam> page, CompanyParam param);

    @InterceptorIgnore(tenantLine = "true")
    Company getCompanyAll(@Param("companyId") Integer companyId);

    @InterceptorIgnore(tenantLine = "true")
    void updateByCompanyId(@Param("param") Company company);

    @InterceptorIgnore(tenantLine = "true")
    boolean removeCompanyAll(Integer id);

    @InterceptorIgnore(tenantLine = "true")
    boolean undeleteAll(Integer id);

    @InterceptorIgnore(tenantLine = "true")
    boolean updateByIdAll(Company company);

    @InterceptorIgnore(tenantLine = "true")
    Company getByTenantId(Integer tenantId);
}
