package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopGoodsRelationMapper;
import com.gxwebsoft.shop.service.ShopGoodsRelationService;
import com.gxwebsoft.shop.entity.ShopGoodsRelation;
import com.gxwebsoft.shop.param.ShopGoodsRelationParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品点赞和收藏表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopGoodsRelationServiceImpl extends ServiceImpl<ShopGoodsRelationMapper, ShopGoodsRelation> implements ShopGoodsRelationService {

    @Override
    public PageResult<ShopGoodsRelation> pageRel(ShopGoodsRelationParam param) {
        PageParam<ShopGoodsRelation, ShopGoodsRelationParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopGoodsRelation> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopGoodsRelation> listRel(ShopGoodsRelationParam param) {
        List<ShopGoodsRelation> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopGoodsRelation, ShopGoodsRelationParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopGoodsRelation getByIdRel(Integer id) {
        ShopGoodsRelationParam param = new ShopGoodsRelationParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
