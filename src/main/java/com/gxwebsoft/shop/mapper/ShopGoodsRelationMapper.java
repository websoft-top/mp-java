package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopGoodsRelation;
import com.gxwebsoft.shop.param.ShopGoodsRelationParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品点赞和收藏表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopGoodsRelationMapper extends BaseMapper<ShopGoodsRelation> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopGoodsRelation>
     */
    List<ShopGoodsRelation> selectPageRel(@Param("page") IPage<ShopGoodsRelation> page,
                             @Param("param") ShopGoodsRelationParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopGoodsRelation> selectListRel(@Param("param") ShopGoodsRelationParam param);

}
