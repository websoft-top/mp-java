package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsArticle;
import com.gxwebsoft.cms.param.CmsArticleParam;

import javax.validation.Valid;
import java.util.List;

/**
 * 文章Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsArticleService extends IService<CmsArticle> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsArticle>
     */
    PageResult<CmsArticle> pageRel(CmsArticleParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsArticle>
     */
    List<CmsArticle> listRel(CmsArticleParam param);

    /**
     * 根据id查询
     *
     * @param articleId 文章ID
     * @return CmsArticle
     */
    CmsArticle getByIdRel(Integer articleId);

    void saveInc(Integer formId);

    boolean saveRel(@Valid CmsArticle article);

    boolean updateByIdRel(CmsArticle article);
}
