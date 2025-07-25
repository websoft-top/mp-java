package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopRechargeOrder;
import com.gxwebsoft.shop.param.ShopRechargeOrderParam;

import java.util.List;

/**
 * 会员充值订单表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopRechargeOrderService extends IService<ShopRechargeOrder> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopRechargeOrder>
     */
    PageResult<ShopRechargeOrder> pageRel(ShopRechargeOrderParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopRechargeOrder>
     */
    List<ShopRechargeOrder> listRel(ShopRechargeOrderParam param);

    /**
     * 根据id查询
     *
     * @param orderId 订单ID
     * @return ShopRechargeOrder
     */
    ShopRechargeOrder getByIdRel(Integer orderId);

}
