package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsMpAd;
import com.gxwebsoft.cms.param.CmsMpAdParam;

import java.util.List;

/**
 * 小程序广告位Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsMpAdService extends IService<CmsMpAd> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsMpAd>
     */
    PageResult<CmsMpAd> pageRel(CmsMpAdParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsMpAd>
     */
    List<CmsMpAd> listRel(CmsMpAdParam param);

    /**
     * 根据id查询
     *
     * @param adId ID
     * @return CmsMpAd
     */
    CmsMpAd getByIdRel(Integer adId);

}
