package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopRechargeOrderMapper;
import com.gxwebsoft.shop.service.ShopRechargeOrderService;
import com.gxwebsoft.shop.entity.ShopRechargeOrder;
import com.gxwebsoft.shop.param.ShopRechargeOrderParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员充值订单表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopRechargeOrderServiceImpl extends ServiceImpl<ShopRechargeOrderMapper, ShopRechargeOrder> implements ShopRechargeOrderService {

    @Override
    public PageResult<ShopRechargeOrder> pageRel(ShopRechargeOrderParam param) {
        PageParam<ShopRechargeOrder, ShopRechargeOrderParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopRechargeOrder> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopRechargeOrder> listRel(ShopRechargeOrderParam param) {
        List<ShopRechargeOrder> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopRechargeOrder, ShopRechargeOrderParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopRechargeOrder getByIdRel(Integer orderId) {
        ShopRechargeOrderParam param = new ShopRechargeOrderParam();
        param.setOrderId(orderId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
