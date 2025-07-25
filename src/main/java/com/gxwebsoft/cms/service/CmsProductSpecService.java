package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsProductSpec;
import com.gxwebsoft.cms.param.CmsProductSpecParam;

import java.util.List;

/**
 * 规格Service
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
public interface CmsProductSpecService extends IService<CmsProductSpec> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsProductSpec>
     */
    PageResult<CmsProductSpec> pageRel(CmsProductSpecParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsProductSpec>
     */
    List<CmsProductSpec> listRel(CmsProductSpecParam param);

    /**
     * 根据id查询
     *
     * @param specId 规格ID
     * @return CmsProductSpec
     */
    CmsProductSpec getByIdRel(Integer specId);

}
