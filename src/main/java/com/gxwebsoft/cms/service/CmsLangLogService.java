package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsLangLog;
import com.gxwebsoft.cms.param.CmsLangLogParam;

import java.util.List;

/**
 * 国际化记录启用Service
 *
 * @author 科技小王子
 * @since 2025-01-06 19:29:26
 */
public interface CmsLangLogService extends IService<CmsLangLog> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsLangLog>
     */
    PageResult<CmsLangLog> pageRel(CmsLangLogParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsLangLog>
     */
    List<CmsLangLog> listRel(CmsLangLogParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return CmsLangLog
     */
    CmsLangLog getByIdRel(Integer id);

}
