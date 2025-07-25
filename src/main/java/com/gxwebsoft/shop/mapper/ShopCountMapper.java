package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopCount;
import com.gxwebsoft.shop.param.ShopCountParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商城销售统计表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopCountMapper extends BaseMapper<ShopCount> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopCount>
     */
    List<ShopCount> selectPageRel(@Param("page") IPage<ShopCount> page,
                             @Param("param") ShopCountParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopCount> selectListRel(@Param("param") ShopCountParam param);

}
