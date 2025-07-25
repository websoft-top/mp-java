package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsTemplate;
import com.gxwebsoft.cms.param.CmsTemplateParam;

import java.util.List;

/**
 * 网站模版Service
 *
 * @author 科技小王子
 * @since 2025-01-21 14:21:16
 */
public interface CmsTemplateService extends IService<CmsTemplate> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsTemplate>
     */
    PageResult<CmsTemplate> pageRel(CmsTemplateParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsTemplate>
     */
    List<CmsTemplate> listRel(CmsTemplateParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return CmsTemplate
     */
    CmsTemplate getByIdRel(Integer id);

}
