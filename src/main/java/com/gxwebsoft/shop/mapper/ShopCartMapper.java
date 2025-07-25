package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopCart;
import com.gxwebsoft.shop.param.ShopCartParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopCartMapper extends BaseMapper<ShopCart> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopCart>
     */
    List<ShopCart> selectPageRel(@Param("page") IPage<ShopCart> page,
                             @Param("param") ShopCartParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopCart> selectListRel(@Param("param") ShopCartParam param);

}
