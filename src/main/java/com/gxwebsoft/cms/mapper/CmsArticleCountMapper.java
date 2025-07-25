package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsArticleCount;
import com.gxwebsoft.cms.param.CmsArticleCountParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 点赞文章Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsArticleCountMapper extends BaseMapper<CmsArticleCount> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsArticleCount>
     */
    List<CmsArticleCount> selectPageRel(@Param("page") IPage<CmsArticleCount> page,
                             @Param("param") CmsArticleCountParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsArticleCount> selectListRel(@Param("param") CmsArticleCountParam param);

}
