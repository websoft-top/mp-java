package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopOrderInfoLog;
import com.gxwebsoft.shop.param.ShopOrderInfoLogParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单核销Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderInfoLogMapper extends BaseMapper<ShopOrderInfoLog> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopOrderInfoLog>
     */
    List<ShopOrderInfoLog> selectPageRel(@Param("page") IPage<ShopOrderInfoLog> page,
                             @Param("param") ShopOrderInfoLogParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopOrderInfoLog> selectListRel(@Param("param") ShopOrderInfoLogParam param);

}
