package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopGoodsComment;
import com.gxwebsoft.shop.param.ShopGoodsCommentParam;

import java.util.List;

/**
 * 评论表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopGoodsCommentService extends IService<ShopGoodsComment> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopGoodsComment>
     */
    PageResult<ShopGoodsComment> pageRel(ShopGoodsCommentParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopGoodsComment>
     */
    List<ShopGoodsComment> listRel(ShopGoodsCommentParam param);

    /**
     * 根据id查询
     *
     * @param id 评论ID
     * @return ShopGoodsComment
     */
    ShopGoodsComment getByIdRel(Integer id);

}
