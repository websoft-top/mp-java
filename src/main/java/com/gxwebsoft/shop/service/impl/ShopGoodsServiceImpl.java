package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopGoodsMapper;
import com.gxwebsoft.shop.service.ShopGoodsService;
import com.gxwebsoft.shop.entity.ShopGoods;
import com.gxwebsoft.shop.param.ShopGoodsParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品Service实现
 *
 * @author 科技小王子
 * @since 2025-04-24 20:52:13
 */
@Service
public class ShopGoodsServiceImpl extends ServiceImpl<ShopGoodsMapper, ShopGoods> implements ShopGoodsService {

    @Override
    public PageResult<ShopGoods> pageRel(ShopGoodsParam param) {
        PageParam<ShopGoods, ShopGoodsParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopGoods> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopGoods> listRel(ShopGoodsParam param) {
        List<ShopGoods> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopGoods, ShopGoodsParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopGoods getByIdRel(Integer goodsId) {
        ShopGoodsParam param = new ShopGoodsParam();
        param.setGoodsId(goodsId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
