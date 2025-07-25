package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsNavigation;
import com.gxwebsoft.cms.param.CmsNavigationParam;

import java.util.List;

/**
 * 网站导航记录表Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsNavigationService extends IService<CmsNavigation> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsNavigation>
     */
    PageResult<CmsNavigation> pageRel(CmsNavigationParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsNavigation>
     */
    List<CmsNavigation> listRel(CmsNavigationParam param);

    /**
     * 根据id查询
     *
     * @param navigationId ID
     * @return CmsNavigation
     */
    CmsNavigation getByIdRel(Integer navigationId);

    void saveAsync(CmsNavigation cmsNavigation);
}
