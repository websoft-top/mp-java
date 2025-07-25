package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopGoodsCategoryMapper;
import com.gxwebsoft.shop.service.ShopGoodsCategoryService;
import com.gxwebsoft.shop.entity.ShopGoodsCategory;
import com.gxwebsoft.shop.param.ShopGoodsCategoryParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类Service实现
 *
 * @author 科技小王子
 * @since 2025-05-01 00:36:45
 */
@Service
public class ShopGoodsCategoryServiceImpl extends ServiceImpl<ShopGoodsCategoryMapper, ShopGoodsCategory> implements ShopGoodsCategoryService {

    @Override
    public PageResult<ShopGoodsCategory> pageRel(ShopGoodsCategoryParam param) {
        PageParam<ShopGoodsCategory, ShopGoodsCategoryParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopGoodsCategory> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopGoodsCategory> listRel(ShopGoodsCategoryParam param) {
        List<ShopGoodsCategory> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopGoodsCategory, ShopGoodsCategoryParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopGoodsCategory getByIdRel(Integer categoryId) {
        ShopGoodsCategoryParam param = new ShopGoodsCategoryParam();
        param.setCategoryId(categoryId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
