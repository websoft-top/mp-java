package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsArticleCategory;
import com.gxwebsoft.cms.param.CmsArticleCategoryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章分类表Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsArticleCategoryMapper extends BaseMapper<CmsArticleCategory> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsArticleCategory>
     */
    List<CmsArticleCategory> selectPageRel(@Param("page") IPage<CmsArticleCategory> page,
                             @Param("param") CmsArticleCategoryParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsArticleCategory> selectListRel(@Param("param") CmsArticleCategoryParam param);

}
