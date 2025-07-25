package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyParameter;
import com.gxwebsoft.common.system.param.CompanyParameterParam;

import java.util.List;

/**
 * 应用参数Service
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
public interface CompanyParameterService extends IService<CompanyParameter> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CompanyParameter>
     */
    PageResult<CompanyParameter> pageRel(CompanyParameterParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CompanyParameter>
     */
    List<CompanyParameter> listRel(CompanyParameterParam param);

    /**
     * 根据id查询
     *
     * @param id 自增ID
     * @return CompanyParameter
     */
    CompanyParameter getByIdRel(Integer id);

}
