package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopOrderInfo;
import com.gxwebsoft.shop.param.ShopOrderInfoParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 场地Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderInfoMapper extends BaseMapper<ShopOrderInfo> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopOrderInfo>
     */
    List<ShopOrderInfo> selectPageRel(@Param("page") IPage<ShopOrderInfo> page,
                             @Param("param") ShopOrderInfoParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopOrderInfo> selectListRel(@Param("param") ShopOrderInfoParam param);

}
