package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopOrder;
import com.gxwebsoft.shop.param.ShopOrderParam;

import java.util.HashMap;
import java.util.List;

/**
 * 订单Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderService extends IService<ShopOrder> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopOrder>
     */
    PageResult<ShopOrder> pageRel(ShopOrderParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopOrder>
     */
    List<ShopOrder> listRel(ShopOrderParam param);

    /**
     * 根据id查询
     *
     * @param orderId 订单号
     * @return ShopOrder
     */
    ShopOrder getByIdRel(Integer orderId);

    HashMap<String, String> createWxOrder(ShopOrder shopOrder);

    ShopOrder getByOutTradeNo(String outTradeNo);

    Boolean queryOrderByOutTradeNo(ShopOrder shopOrder);

    void updateByOutTradeNo(ShopOrder order);
}
