package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsComponents;
import com.gxwebsoft.cms.param.CmsComponentsParam;

import java.util.List;

/**
 * 组件Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsComponentsService extends IService<CmsComponents> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsComponents>
     */
    PageResult<CmsComponents> pageRel(CmsComponentsParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsComponents>
     */
    List<CmsComponents> listRel(CmsComponentsParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return CmsComponents
     */
    CmsComponents getByIdRel(Integer id);

}
