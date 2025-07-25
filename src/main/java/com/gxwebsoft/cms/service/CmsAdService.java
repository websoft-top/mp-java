package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsAd;
import com.gxwebsoft.cms.param.CmsAdParam;

import java.util.List;

/**
 * 广告位Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsAdService extends IService<CmsAd> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsAd>
     */
    PageResult<CmsAd> pageRel(CmsAdParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsAd>
     */
    List<CmsAd> listRel(CmsAdParam param);

    /**
     * 根据id查询
     *
     * @param adId ID
     * @return CmsAd
     */
    CmsAd getByIdRel(Integer adId);

}
