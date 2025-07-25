package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopGoodsCoupon;
import com.gxwebsoft.shop.param.ShopGoodsCouponParam;

import java.util.List;

/**
 * 商品优惠券表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopGoodsCouponService extends IService<ShopGoodsCoupon> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopGoodsCoupon>
     */
    PageResult<ShopGoodsCoupon> pageRel(ShopGoodsCouponParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopGoodsCoupon>
     */
    List<ShopGoodsCoupon> listRel(ShopGoodsCouponParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return ShopGoodsCoupon
     */
    ShopGoodsCoupon getByIdRel(Integer id);

}
