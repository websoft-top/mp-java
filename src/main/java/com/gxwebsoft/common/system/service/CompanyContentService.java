package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyContent;
import com.gxwebsoft.common.system.param.CompanyContentParam;

import java.util.List;

/**
 * 应用详情Service
 *
 * @author 科技小王子
 * @since 2024-10-16 13:41:21
 */
public interface CompanyContentService extends IService<CompanyContent> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CompanyContent>
     */
    PageResult<CompanyContent> pageRel(CompanyContentParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CompanyContent>
     */
    List<CompanyContent> listRel(CompanyContentParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return CompanyContent
     */
    CompanyContent getByIdRel(Integer id);

}
