package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopGoodsRelation;
import com.gxwebsoft.shop.param.ShopGoodsRelationParam;

import java.util.List;

/**
 * 商品点赞和收藏表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopGoodsRelationService extends IService<ShopGoodsRelation> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopGoodsRelation>
     */
    PageResult<ShopGoodsRelation> pageRel(ShopGoodsRelationParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopGoodsRelation>
     */
    List<ShopGoodsRelation> listRel(ShopGoodsRelationParam param);

    /**
     * 根据id查询
     *
     * @param id id
     * @return ShopGoodsRelation
     */
    ShopGoodsRelation getByIdRel(Integer id);

}
