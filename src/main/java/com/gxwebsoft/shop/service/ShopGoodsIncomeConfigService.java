package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopGoodsIncomeConfig;
import com.gxwebsoft.shop.param.ShopGoodsIncomeConfigParam;

import java.util.List;

/**
 * 分润配置Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopGoodsIncomeConfigService extends IService<ShopGoodsIncomeConfig> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopGoodsIncomeConfig>
     */
    PageResult<ShopGoodsIncomeConfig> pageRel(ShopGoodsIncomeConfigParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopGoodsIncomeConfig>
     */
    List<ShopGoodsIncomeConfig> listRel(ShopGoodsIncomeConfigParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return ShopGoodsIncomeConfig
     */
    ShopGoodsIncomeConfig getByIdRel(Integer id);

}
