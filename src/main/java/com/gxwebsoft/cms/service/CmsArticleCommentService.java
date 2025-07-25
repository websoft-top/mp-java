package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsArticleComment;
import com.gxwebsoft.cms.param.CmsArticleCommentParam;

import java.util.List;

/**
 * 文章评论表Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsArticleCommentService extends IService<CmsArticleComment> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsArticleComment>
     */
    PageResult<CmsArticleComment> pageRel(CmsArticleCommentParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsArticleComment>
     */
    List<CmsArticleComment> listRel(CmsArticleCommentParam param);

    /**
     * 根据id查询
     *
     * @param commentId 评价ID
     * @return CmsArticleComment
     */
    CmsArticleComment getByIdRel(Integer commentId);

}
