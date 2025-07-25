package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopSpec;
import com.gxwebsoft.shop.param.ShopSpecParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规格Mapper
 *
 * @author 科技小王子
 * @since 2025-05-01 09:44:00
 */
public interface ShopSpecMapper extends BaseMapper<ShopSpec> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopSpec>
     */
    List<ShopSpec> selectPageRel(@Param("page") IPage<ShopSpec> page,
                             @Param("param") ShopSpecParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopSpec> selectListRel(@Param("param") ShopSpecParam param);

}
