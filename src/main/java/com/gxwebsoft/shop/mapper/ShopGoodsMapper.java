package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopGoods;
import com.gxwebsoft.shop.param.ShopGoodsParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品Mapper
 *
 * @author 科技小王子
 * @since 2025-04-24 20:52:13
 */
public interface ShopGoodsMapper extends BaseMapper<ShopGoods> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopGoods>
     */
    List<ShopGoods> selectPageRel(@Param("page") IPage<ShopGoods> page,
                             @Param("param") ShopGoodsParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopGoods> selectListRel(@Param("param") ShopGoodsParam param);

}
