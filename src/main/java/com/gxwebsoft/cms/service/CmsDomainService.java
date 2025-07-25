package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsDomain;
import com.gxwebsoft.cms.param.CmsDomainParam;

import java.util.List;

/**
 * 网站域名记录表Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
public interface CmsDomainService extends IService<CmsDomain> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsDomain>
     */
    PageResult<CmsDomain> pageRel(CmsDomainParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsDomain>
     */
    List<CmsDomain> listRel(CmsDomainParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return CmsDomain
     */
    CmsDomain getByIdRel(Integer id);

}
