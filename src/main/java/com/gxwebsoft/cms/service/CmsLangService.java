package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsLang;
import com.gxwebsoft.cms.param.CmsLangParam;

import java.util.List;

/**
 * 国际化Service
 *
 * @author 科技小王子
 * @since 2025-01-06 19:29:26
 */
public interface CmsLangService extends IService<CmsLang> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsLang>
     */
    PageResult<CmsLang> pageRel(CmsLangParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsLang>
     */
    List<CmsLang> listRel(CmsLangParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return CmsLang
     */
    CmsLang getByIdRel(Integer id);

}
