package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopOrderGoods;
import com.gxwebsoft.shop.param.ShopOrderGoodsParam;

import java.util.List;

/**
 * 商品信息Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderGoodsService extends IService<ShopOrderGoods> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopOrderGoods>
     */
    PageResult<ShopOrderGoods> pageRel(ShopOrderGoodsParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopOrderGoods>
     */
    List<ShopOrderGoods> listRel(ShopOrderGoodsParam param);

    /**
     * 根据id查询
     *
     * @param id 自增ID
     * @return ShopOrderGoods
     */
    ShopOrderGoods getByIdRel(Integer id);

}
