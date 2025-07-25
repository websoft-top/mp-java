package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsWebsiteSetting;
import com.gxwebsoft.cms.param.CmsWebsiteSettingParam;

import java.util.List;

/**
 * 网站设置Service
 *
 * @author 科技小王子
 * @since 2025-02-19 01:35:44
 */
public interface CmsWebsiteSettingService extends IService<CmsWebsiteSetting> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsWebsiteSetting>
     */
    PageResult<CmsWebsiteSetting> pageRel(CmsWebsiteSettingParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsWebsiteSetting>
     */
    List<CmsWebsiteSetting> listRel(CmsWebsiteSettingParam param);

    /**
     * 根据id查询
     *
     * @param id 自增ID
     * @return CmsWebsiteSetting
     */
    CmsWebsiteSetting getByIdRel(Integer id);

}
