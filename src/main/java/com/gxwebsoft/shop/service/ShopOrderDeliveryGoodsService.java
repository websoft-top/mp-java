package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopOrderDeliveryGoods;
import com.gxwebsoft.shop.param.ShopOrderDeliveryGoodsParam;

import java.util.List;

/**
 * 发货单商品Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderDeliveryGoodsService extends IService<ShopOrderDeliveryGoods> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopOrderDeliveryGoods>
     */
    PageResult<ShopOrderDeliveryGoods> pageRel(ShopOrderDeliveryGoodsParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopOrderDeliveryGoods>
     */
    List<ShopOrderDeliveryGoods> listRel(ShopOrderDeliveryGoodsParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return ShopOrderDeliveryGoods
     */
    ShopOrderDeliveryGoods getByIdRel(Integer id);

}
