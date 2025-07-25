package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopOrderDeliveryGoodsMapper;
import com.gxwebsoft.shop.service.ShopOrderDeliveryGoodsService;
import com.gxwebsoft.shop.entity.ShopOrderDeliveryGoods;
import com.gxwebsoft.shop.param.ShopOrderDeliveryGoodsParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发货单商品Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopOrderDeliveryGoodsServiceImpl extends ServiceImpl<ShopOrderDeliveryGoodsMapper, ShopOrderDeliveryGoods> implements ShopOrderDeliveryGoodsService {

    @Override
    public PageResult<ShopOrderDeliveryGoods> pageRel(ShopOrderDeliveryGoodsParam param) {
        PageParam<ShopOrderDeliveryGoods, ShopOrderDeliveryGoodsParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopOrderDeliveryGoods> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopOrderDeliveryGoods> listRel(ShopOrderDeliveryGoodsParam param) {
        List<ShopOrderDeliveryGoods> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopOrderDeliveryGoods, ShopOrderDeliveryGoodsParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopOrderDeliveryGoods getByIdRel(Integer id) {
        ShopOrderDeliveryGoodsParam param = new ShopOrderDeliveryGoodsParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
