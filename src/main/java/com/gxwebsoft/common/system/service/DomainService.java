package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Domain;
import com.gxwebsoft.common.system.param.DomainParam;

import java.util.List;

/**
 * 授权域名Service
 *
 * @author 科技小王子
 * @since 2024-09-19 23:56:33
 */
public interface DomainService extends IService<Domain> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<Domain>
     */
    PageResult<Domain> pageRel(DomainParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<Domain>
     */
    List<Domain> listRel(DomainParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return Domain
     */
    Domain getByIdRel(Integer id);

}
