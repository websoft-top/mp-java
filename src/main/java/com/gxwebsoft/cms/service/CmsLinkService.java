package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsLink;
import com.gxwebsoft.cms.param.CmsLinkParam;

import java.util.List;

/**
 * 常用链接Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsLinkService extends IService<CmsLink> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsLink>
     */
    PageResult<CmsLink> pageRel(CmsLinkParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsLink>
     */
    List<CmsLink> listRel(CmsLinkParam param);

    /**
     * 根据id查询
     *
     * @param id 自增ID
     * @return CmsLink
     */
    CmsLink getByIdRel(Integer id);

}
