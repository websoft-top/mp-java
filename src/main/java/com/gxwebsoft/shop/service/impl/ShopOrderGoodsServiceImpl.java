package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopOrderGoodsMapper;
import com.gxwebsoft.shop.service.ShopOrderGoodsService;
import com.gxwebsoft.shop.entity.ShopOrderGoods;
import com.gxwebsoft.shop.param.ShopOrderGoodsParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品信息Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopOrderGoodsServiceImpl extends ServiceImpl<ShopOrderGoodsMapper, ShopOrderGoods> implements ShopOrderGoodsService {

    @Override
    public PageResult<ShopOrderGoods> pageRel(ShopOrderGoodsParam param) {
        PageParam<ShopOrderGoods, ShopOrderGoodsParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopOrderGoods> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopOrderGoods> listRel(ShopOrderGoodsParam param) {
        List<ShopOrderGoods> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopOrderGoods, ShopOrderGoodsParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopOrderGoods getByIdRel(Integer id) {
        ShopOrderGoodsParam param = new ShopOrderGoodsParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
