package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopOrderDeliveryGoods;
import com.gxwebsoft.shop.param.ShopOrderDeliveryGoodsParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 发货单商品Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderDeliveryGoodsMapper extends BaseMapper<ShopOrderDeliveryGoods> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopOrderDeliveryGoods>
     */
    List<ShopOrderDeliveryGoods> selectPageRel(@Param("page") IPage<ShopOrderDeliveryGoods> page,
                             @Param("param") ShopOrderDeliveryGoodsParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopOrderDeliveryGoods> selectListRel(@Param("param") ShopOrderDeliveryGoodsParam param);

}
