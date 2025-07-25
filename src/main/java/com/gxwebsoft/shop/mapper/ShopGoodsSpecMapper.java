package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopGoodsSpec;
import com.gxwebsoft.shop.param.ShopGoodsSpecParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品多规格Mapper
 *
 * @author 科技小王子
 * @since 2025-05-01 09:43:31
 */
public interface ShopGoodsSpecMapper extends BaseMapper<ShopGoodsSpec> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopGoodsSpec>
     */
    List<ShopGoodsSpec> selectPageRel(@Param("page") IPage<ShopGoodsSpec> page,
                             @Param("param") ShopGoodsSpecParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopGoodsSpec> selectListRel(@Param("param") ShopGoodsSpecParam param);

}
