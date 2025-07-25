package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopGoodsSkuMapper;
import com.gxwebsoft.shop.service.ShopGoodsSkuService;
import com.gxwebsoft.shop.entity.ShopGoodsSku;
import com.gxwebsoft.shop.param.ShopGoodsSkuParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品sku列表Service实现
 *
 * @author 科技小王子
 * @since 2025-05-01 09:43:31
 */
@Service
public class ShopGoodsSkuServiceImpl extends ServiceImpl<ShopGoodsSkuMapper, ShopGoodsSku> implements ShopGoodsSkuService {

    @Override
    public PageResult<ShopGoodsSku> pageRel(ShopGoodsSkuParam param) {
        PageParam<ShopGoodsSku, ShopGoodsSkuParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopGoodsSku> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopGoodsSku> listRel(ShopGoodsSkuParam param) {
        List<ShopGoodsSku> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopGoodsSku, ShopGoodsSkuParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopGoodsSku getByIdRel(Integer id) {
        ShopGoodsSkuParam param = new ShopGoodsSkuParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
