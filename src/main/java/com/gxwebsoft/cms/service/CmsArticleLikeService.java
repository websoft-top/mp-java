package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsArticleLike;
import com.gxwebsoft.cms.param.CmsArticleLikeParam;

import java.util.List;

/**
 * 点赞文章Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsArticleLikeService extends IService<CmsArticleLike> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsArticleLike>
     */
    PageResult<CmsArticleLike> pageRel(CmsArticleLikeParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsArticleLike>
     */
    List<CmsArticleLike> listRel(CmsArticleLikeParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return CmsArticleLike
     */
    CmsArticleLike getByIdRel(Integer id);

}
