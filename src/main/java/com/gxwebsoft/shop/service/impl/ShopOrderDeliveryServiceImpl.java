package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopOrderDeliveryMapper;
import com.gxwebsoft.shop.service.ShopOrderDeliveryService;
import com.gxwebsoft.shop.entity.ShopOrderDelivery;
import com.gxwebsoft.shop.param.ShopOrderDeliveryParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发货单Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopOrderDeliveryServiceImpl extends ServiceImpl<ShopOrderDeliveryMapper, ShopOrderDelivery> implements ShopOrderDeliveryService {

    @Override
    public PageResult<ShopOrderDelivery> pageRel(ShopOrderDeliveryParam param) {
        PageParam<ShopOrderDelivery, ShopOrderDeliveryParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopOrderDelivery> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopOrderDelivery> listRel(ShopOrderDeliveryParam param) {
        List<ShopOrderDelivery> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopOrderDelivery, ShopOrderDeliveryParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopOrderDelivery getByIdRel(Integer deliveryId) {
        ShopOrderDeliveryParam param = new ShopOrderDeliveryParam();
        param.setDeliveryId(deliveryId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
