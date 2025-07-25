package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsArticleCount;
import com.gxwebsoft.cms.param.CmsArticleCountParam;

import java.util.List;

/**
 * 点赞文章Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsArticleCountService extends IService<CmsArticleCount> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsArticleCount>
     */
    PageResult<CmsArticleCount> pageRel(CmsArticleCountParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsArticleCount>
     */
    List<CmsArticleCount> listRel(CmsArticleCountParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return CmsArticleCount
     */
    CmsArticleCount getByIdRel(Integer id);

}
