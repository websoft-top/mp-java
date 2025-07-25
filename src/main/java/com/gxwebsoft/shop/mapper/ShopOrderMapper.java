package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopOrder;
import com.gxwebsoft.shop.param.ShopOrderParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderMapper extends BaseMapper<ShopOrder> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopOrder>
     */
    List<ShopOrder> selectPageRel(@Param("page") IPage<ShopOrder> page,
                             @Param("param") ShopOrderParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopOrder> selectListRel(@Param("param") ShopOrderParam param);

    @InterceptorIgnore(tenantLine = "true")
    ShopOrder getByOutTradeNo(@Param("outTradeNo") String outTradeNo);

    @InterceptorIgnore(tenantLine = "true")
    void updateByOutTradeNo(@Param("param") ShopOrder order);
}
