package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopRechargeOrder;
import com.gxwebsoft.shop.param.ShopRechargeOrderParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员充值订单表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopRechargeOrderMapper extends BaseMapper<ShopRechargeOrder> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopRechargeOrder>
     */
    List<ShopRechargeOrder> selectPageRel(@Param("page") IPage<ShopRechargeOrder> page,
                             @Param("param") ShopRechargeOrderParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopRechargeOrder> selectListRel(@Param("param") ShopRechargeOrderParam param);

}
