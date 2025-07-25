package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopGoodsIncomeConfig;
import com.gxwebsoft.shop.param.ShopGoodsIncomeConfigParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分润配置Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopGoodsIncomeConfigMapper extends BaseMapper<ShopGoodsIncomeConfig> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopGoodsIncomeConfig>
     */
    List<ShopGoodsIncomeConfig> selectPageRel(@Param("page") IPage<ShopGoodsIncomeConfig> page,
                             @Param("param") ShopGoodsIncomeConfigParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopGoodsIncomeConfig> selectListRel(@Param("param") ShopGoodsIncomeConfigParam param);

}
