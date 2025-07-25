package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.CompanyUrl;
import com.gxwebsoft.common.system.param.CompanyUrlParam;

import java.util.List;

/**
 * 应用域名Service
 *
 * @author 科技小王子
 * @since 2024-10-17 15:30:24
 */
public interface CompanyUrlService extends IService<CompanyUrl> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CompanyUrl>
     */
    PageResult<CompanyUrl> pageRel(CompanyUrlParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CompanyUrl>
     */
    List<CompanyUrl> listRel(CompanyUrlParam param);

    /**
     * 根据id查询
     *
     * @param id 自增ID
     * @return CompanyUrl
     */
    CompanyUrl getByIdRel(Integer id);

}
