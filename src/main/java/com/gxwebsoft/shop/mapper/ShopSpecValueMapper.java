package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopSpecValue;
import com.gxwebsoft.shop.param.ShopSpecValueParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规格值Mapper
 *
 * @author 科技小王子
 * @since 2025-05-01 09:44:00
 */
public interface ShopSpecValueMapper extends BaseMapper<ShopSpecValue> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopSpecValue>
     */
    List<ShopSpecValue> selectPageRel(@Param("page") IPage<ShopSpecValue> page,
                             @Param("param") ShopSpecValueParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopSpecValue> selectListRel(@Param("param") ShopSpecValueParam param);

}
