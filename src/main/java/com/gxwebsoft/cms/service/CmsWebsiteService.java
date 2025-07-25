package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsWebsite;
import com.gxwebsoft.cms.param.CmsWebsiteParam;
import com.gxwebsoft.common.system.entity.User;

import java.util.List;

/**
 * 网站信息记录表Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
public interface CmsWebsiteService extends IService<CmsWebsite> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsWebsite>
     */
    PageResult<CmsWebsite> pageRel(CmsWebsiteParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsWebsite>
     */
    List<CmsWebsite> listRel(CmsWebsiteParam param);

    /**
     * 根据id查询
     *
     * @param websiteId 站点ID
     * @return CmsWebsite
     */
    CmsWebsite getByIdRel(Integer websiteId);

    PageResult<CmsWebsite> pageRelAll(CmsWebsiteParam param);

    // 创建站点
    CmsWebsite create(CmsWebsite cmsWebsite);

    CmsWebsite getByIdRelAll(Integer id);

    boolean updateByIdAll(CmsWebsite cmsWebsite);

    boolean removeByIdAll(Integer id);

    CmsWebsite getByTenantId(Integer tenantId);
}
