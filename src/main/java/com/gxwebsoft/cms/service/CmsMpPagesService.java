package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsMpPages;
import com.gxwebsoft.cms.param.CmsMpPagesParam;

import java.util.List;

/**
 * 小程序页面Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsMpPagesService extends IService<CmsMpPages> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsMpPages>
     */
    PageResult<CmsMpPages> pageRel(CmsMpPagesParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsMpPages>
     */
    List<CmsMpPages> listRel(CmsMpPagesParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return CmsMpPages
     */
    CmsMpPages getByIdRel(Integer id);

}
