package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsArticleComment;
import com.gxwebsoft.cms.param.CmsArticleCommentParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章评论表Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsArticleCommentMapper extends BaseMapper<CmsArticleComment> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsArticleComment>
     */
    List<CmsArticleComment> selectPageRel(@Param("page") IPage<CmsArticleComment> page,
                             @Param("param") CmsArticleCommentParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsArticleComment> selectListRel(@Param("param") CmsArticleCommentParam param);

}
