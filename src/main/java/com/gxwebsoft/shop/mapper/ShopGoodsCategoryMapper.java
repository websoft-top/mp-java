package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopGoodsCategory;
import com.gxwebsoft.shop.param.ShopGoodsCategoryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类Mapper
 *
 * @author 科技小王子
 * @since 2025-05-01 00:36:45
 */
public interface ShopGoodsCategoryMapper extends BaseMapper<ShopGoodsCategory> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopGoodsCategory>
     */
    List<ShopGoodsCategory> selectPageRel(@Param("page") IPage<ShopGoodsCategory> page,
                             @Param("param") ShopGoodsCategoryParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopGoodsCategory> selectListRel(@Param("param") ShopGoodsCategoryParam param);

}
