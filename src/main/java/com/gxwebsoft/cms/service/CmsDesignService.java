package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsDesign;
import com.gxwebsoft.cms.param.CmsDesignParam;

import java.util.List;

/**
 * 页面管理记录表Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsDesignService extends IService<CmsDesign> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsDesign>
     */
    PageResult<CmsDesign> pageRel(CmsDesignParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsDesign>
     */
    List<CmsDesign> listRel(CmsDesignParam param);

    /**
     * 根据id查询
     *
     * @param pageId ID
     * @return CmsDesign
     */
    CmsDesign getByIdRel(Integer pageId);

    void translate(CmsDesign cmsDesign);
}
