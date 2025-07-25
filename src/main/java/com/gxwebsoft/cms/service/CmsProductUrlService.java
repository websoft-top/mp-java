package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsProductUrl;
import com.gxwebsoft.cms.param.CmsProductUrlParam;

import java.util.List;

/**
 * 域名Service
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
public interface CmsProductUrlService extends IService<CmsProductUrl> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsProductUrl>
     */
    PageResult<CmsProductUrl> pageRel(CmsProductUrlParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsProductUrl>
     */
    List<CmsProductUrl> listRel(CmsProductUrlParam param);

    /**
     * 根据id查询
     *
     * @param id 自增ID
     * @return CmsProductUrl
     */
    CmsProductUrl getByIdRel(Integer id);

}
