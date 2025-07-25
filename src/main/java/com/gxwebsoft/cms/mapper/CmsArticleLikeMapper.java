package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsArticleLike;
import com.gxwebsoft.cms.param.CmsArticleLikeParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 点赞文章Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsArticleLikeMapper extends BaseMapper<CmsArticleLike> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsArticleLike>
     */
    List<CmsArticleLike> selectPageRel(@Param("page") IPage<CmsArticleLike> page,
                             @Param("param") CmsArticleLikeParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsArticleLike> selectListRel(@Param("param") CmsArticleLikeParam param);

}
