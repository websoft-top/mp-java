package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopGoodsCouponMapper;
import com.gxwebsoft.shop.service.ShopGoodsCouponService;
import com.gxwebsoft.shop.entity.ShopGoodsCoupon;
import com.gxwebsoft.shop.param.ShopGoodsCouponParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品优惠券表Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopGoodsCouponServiceImpl extends ServiceImpl<ShopGoodsCouponMapper, ShopGoodsCoupon> implements ShopGoodsCouponService {

    @Override
    public PageResult<ShopGoodsCoupon> pageRel(ShopGoodsCouponParam param) {
        PageParam<ShopGoodsCoupon, ShopGoodsCouponParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopGoodsCoupon> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopGoodsCoupon> listRel(ShopGoodsCouponParam param) {
        List<ShopGoodsCoupon> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopGoodsCoupon, ShopGoodsCouponParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopGoodsCoupon getByIdRel(Integer id) {
        ShopGoodsCouponParam param = new ShopGoodsCouponParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
