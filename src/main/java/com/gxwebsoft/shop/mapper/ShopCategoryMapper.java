package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopCategory;
import com.gxwebsoft.shop.param.ShopCategoryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类Mapper
 *
 * @author 科技小王子
 * @since 2025-04-24 20:52:13
 */
public interface ShopCategoryMapper extends BaseMapper<ShopCategory> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopCategory>
     */
    List<ShopCategory> selectPageRel(@Param("page") IPage<ShopCategory> page,
                             @Param("param") ShopCategoryParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopCategory> selectListRel(@Param("param") ShopCategoryParam param);

}
