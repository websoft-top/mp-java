package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsWebsiteField;
import com.gxwebsoft.cms.param.CmsWebsiteFieldParam;

import java.util.List;

/**
 * 应用参数Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:36:14
 */
public interface CmsWebsiteFieldService extends IService<CmsWebsiteField> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsWebsiteField>
     */
    PageResult<CmsWebsiteField> pageRel(CmsWebsiteFieldParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsWebsiteField>
     */
    List<CmsWebsiteField> listRel(CmsWebsiteFieldParam param);

    /**
     * 根据id查询
     *
     * @param id 自增ID
     * @return CmsWebsiteField
     */
    CmsWebsiteField getByIdRel(Integer id);

}
