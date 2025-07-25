package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.cms.entity.CmsArticle;
import com.gxwebsoft.cms.entity.TranslateDataVo;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsArticleContent;
import com.gxwebsoft.cms.param.CmsArticleContentParam;

import java.util.List;

/**
 * 文章记录表Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsArticleContentService extends IService<CmsArticleContent> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsArticleContent>
     */
    PageResult<CmsArticleContent> pageRel(CmsArticleContentParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsArticleContent>
     */
    List<CmsArticleContent> listRel(CmsArticleContentParam param);


    CmsArticleContent getByIdRel(Integer id);

    void translate(CmsArticle article);
}
