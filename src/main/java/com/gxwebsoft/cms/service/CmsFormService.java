package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsForm;
import com.gxwebsoft.cms.param.CmsFormParam;

import java.util.List;

/**
 * 表单设计表Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsFormService extends IService<CmsForm> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsForm>
     */
    PageResult<CmsForm> pageRel(CmsFormParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsForm>
     */
    List<CmsForm> listRel(CmsFormParam param);

    /**
     * 根据id查询
     *
     * @param formId ID
     * @return CmsForm
     */
    CmsForm getByIdRel(Integer formId);

}
