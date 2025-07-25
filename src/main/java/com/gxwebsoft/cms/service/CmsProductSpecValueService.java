package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsProductSpecValue;
import com.gxwebsoft.cms.param.CmsProductSpecValueParam;

import java.util.List;

/**
 * 规格值Service
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
public interface CmsProductSpecValueService extends IService<CmsProductSpecValue> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsProductSpecValue>
     */
    PageResult<CmsProductSpecValue> pageRel(CmsProductSpecValueParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsProductSpecValue>
     */
    List<CmsProductSpecValue> listRel(CmsProductSpecValueParam param);

    /**
     * 根据id查询
     *
     * @param specValueId 规格值ID
     * @return CmsProductSpecValue
     */
    CmsProductSpecValue getByIdRel(Integer specValueId);

}
