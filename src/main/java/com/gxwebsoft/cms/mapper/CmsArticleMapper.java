package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsArticle;
import com.gxwebsoft.cms.entity.CmsLangLog;
import com.gxwebsoft.cms.param.CmsArticleParam;
import com.gxwebsoft.cms.param.CmsLangLogParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsArticleMapper extends BaseMapper<CmsArticle> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsArticle>
     */
    List<CmsArticle> selectPageRel(@Param("page") IPage<CmsArticle> page,
                             @Param("param") CmsArticleParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsArticle> selectListRel(@Param("param") CmsArticleParam param);


    @InterceptorIgnore(tenantLine = "true")
    List<CmsArticle> selectListAllRel(@Param("param") CmsArticleParam param);
}
