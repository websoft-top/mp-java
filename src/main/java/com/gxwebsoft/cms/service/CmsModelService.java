package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsModel;
import com.gxwebsoft.cms.param.CmsModelParam;

import java.util.List;

/**
 * 模型Service
 *
 * @author 科技小王子
 * @since 2024-11-26 15:44:53
 */
public interface CmsModelService extends IService<CmsModel> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsModel>
     */
    PageResult<CmsModel> pageRel(CmsModelParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsModel>
     */
    List<CmsModel> listRel(CmsModelParam param);

    /**
     * 根据id查询
     *
     * @param modelId ID
     * @return CmsModel
     */
    CmsModel getByIdRel(Integer modelId);

}
