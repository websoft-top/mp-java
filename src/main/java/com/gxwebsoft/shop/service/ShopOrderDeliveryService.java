package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopOrderDelivery;
import com.gxwebsoft.shop.param.ShopOrderDeliveryParam;

import java.util.List;

/**
 * 发货单Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderDeliveryService extends IService<ShopOrderDelivery> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopOrderDelivery>
     */
    PageResult<ShopOrderDelivery> pageRel(ShopOrderDeliveryParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopOrderDelivery>
     */
    List<ShopOrderDelivery> listRel(ShopOrderDeliveryParam param);

    /**
     * 根据id查询
     *
     * @param deliveryId 发货单ID
     * @return ShopOrderDelivery
     */
    ShopOrderDelivery getByIdRel(Integer deliveryId);

}
