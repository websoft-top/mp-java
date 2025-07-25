package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopGoodsSpecMapper;
import com.gxwebsoft.shop.service.ShopGoodsSpecService;
import com.gxwebsoft.shop.entity.ShopGoodsSpec;
import com.gxwebsoft.shop.param.ShopGoodsSpecParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品多规格Service实现
 *
 * @author 科技小王子
 * @since 2025-05-01 09:43:31
 */
@Service
public class ShopGoodsSpecServiceImpl extends ServiceImpl<ShopGoodsSpecMapper, ShopGoodsSpec> implements ShopGoodsSpecService {

    @Override
    public PageResult<ShopGoodsSpec> pageRel(ShopGoodsSpecParam param) {
        PageParam<ShopGoodsSpec, ShopGoodsSpecParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopGoodsSpec> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopGoodsSpec> listRel(ShopGoodsSpecParam param) {
        List<ShopGoodsSpec> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopGoodsSpec, ShopGoodsSpecParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopGoodsSpec getByIdRel(Integer id) {
        ShopGoodsSpecParam param = new ShopGoodsSpecParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
