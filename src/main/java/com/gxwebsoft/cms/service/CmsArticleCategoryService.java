package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsArticleCategory;
import com.gxwebsoft.cms.param.CmsArticleCategoryParam;

import java.util.List;

/**
 * 文章分类表Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsArticleCategoryService extends IService<CmsArticleCategory> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsArticleCategory>
     */
    PageResult<CmsArticleCategory> pageRel(CmsArticleCategoryParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsArticleCategory>
     */
    List<CmsArticleCategory> listRel(CmsArticleCategoryParam param);

    /**
     * 根据id查询
     *
     * @param categoryId 文章分类ID
     * @return CmsArticleCategory
     */
    CmsArticleCategory getByIdRel(Integer categoryId);

}
