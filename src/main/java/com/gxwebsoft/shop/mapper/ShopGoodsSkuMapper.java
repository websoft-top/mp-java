package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopGoodsSku;
import com.gxwebsoft.shop.param.ShopGoodsSkuParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品sku列表Mapper
 *
 * @author 科技小王子
 * @since 2025-05-01 09:43:31
 */
public interface ShopGoodsSkuMapper extends BaseMapper<ShopGoodsSku> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopGoodsSku>
     */
    List<ShopGoodsSku> selectPageRel(@Param("page") IPage<ShopGoodsSku> page,
                             @Param("param") ShopGoodsSkuParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopGoodsSku> selectListRel(@Param("param") ShopGoodsSkuParam param);

}
