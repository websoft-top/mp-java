package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsArticleContent;
import com.gxwebsoft.cms.param.CmsArticleContentParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章记录表Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsArticleContentMapper extends BaseMapper<CmsArticleContent> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsArticleContent>
     */
    List<CmsArticleContent> selectPageRel(@Param("page") IPage<CmsArticleContent> page,
                             @Param("param") CmsArticleContentParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsArticleContent> selectListRel(@Param("param") CmsArticleContentParam param);

}
