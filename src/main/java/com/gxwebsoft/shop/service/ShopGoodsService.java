package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopGoods;
import com.gxwebsoft.shop.param.ShopGoodsParam;

import java.util.List;

/**
 * 商品Service
 *
 * @author 科技小王子
 * @since 2025-04-24 20:52:13
 */
public interface ShopGoodsService extends IService<ShopGoods> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopGoods>
     */
    PageResult<ShopGoods> pageRel(ShopGoodsParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopGoods>
     */
    List<ShopGoods> listRel(ShopGoodsParam param);

    /**
     * 根据id查询
     *
     * @param goodsId 自增ID
     * @return ShopGoods
     */
    ShopGoods getByIdRel(Integer goodsId);

}
