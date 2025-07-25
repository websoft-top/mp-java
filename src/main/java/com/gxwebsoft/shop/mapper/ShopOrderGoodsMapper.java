package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopOrderGoods;
import com.gxwebsoft.shop.param.ShopOrderGoodsParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品信息Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderGoodsMapper extends BaseMapper<ShopOrderGoods> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopOrderGoods>
     */
    List<ShopOrderGoods> selectPageRel(@Param("page") IPage<ShopOrderGoods> page,
                             @Param("param") ShopOrderGoodsParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopOrderGoods> selectListRel(@Param("param") ShopOrderGoodsParam param);

}
