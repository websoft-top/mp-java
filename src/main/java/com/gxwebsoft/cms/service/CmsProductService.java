package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsProduct;
import com.gxwebsoft.cms.param.CmsProductParam;

import java.util.List;

/**
 * 产品Service
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
public interface CmsProductService extends IService<CmsProduct> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsProduct>
     */
    PageResult<CmsProduct> pageRel(CmsProductParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsProduct>
     */
    List<CmsProduct> listRel(CmsProductParam param);

    /**
     * 根据id查询
     *
     * @param productId 自增ID
     * @return CmsProduct
     */
    CmsProduct getByIdRel(Integer productId);

}
