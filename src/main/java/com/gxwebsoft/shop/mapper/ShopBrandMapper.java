package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopBrand;
import com.gxwebsoft.shop.param.ShopBrandParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopBrandMapper extends BaseMapper<ShopBrand> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopBrand>
     */
    List<ShopBrand> selectPageRel(@Param("page") IPage<ShopBrand> page,
                             @Param("param") ShopBrandParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopBrand> selectListRel(@Param("param") ShopBrandParam param);

}
