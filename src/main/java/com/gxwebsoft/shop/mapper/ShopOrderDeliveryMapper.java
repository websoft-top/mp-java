package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopOrderDelivery;
import com.gxwebsoft.shop.param.ShopOrderDeliveryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 发货单Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderDeliveryMapper extends BaseMapper<ShopOrderDelivery> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopOrderDelivery>
     */
    List<ShopOrderDelivery> selectPageRel(@Param("page") IPage<ShopOrderDelivery> page,
                             @Param("param") ShopOrderDeliveryParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopOrderDelivery> selectListRel(@Param("param") ShopOrderDeliveryParam param);

}
