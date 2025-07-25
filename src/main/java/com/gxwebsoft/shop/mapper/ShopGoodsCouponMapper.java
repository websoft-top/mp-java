package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopGoodsCoupon;
import com.gxwebsoft.shop.param.ShopGoodsCouponParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品优惠券表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopGoodsCouponMapper extends BaseMapper<ShopGoodsCoupon> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopGoodsCoupon>
     */
    List<ShopGoodsCoupon> selectPageRel(@Param("page") IPage<ShopGoodsCoupon> page,
                             @Param("param") ShopGoodsCouponParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopGoodsCoupon> selectListRel(@Param("param") ShopGoodsCouponParam param);

}
