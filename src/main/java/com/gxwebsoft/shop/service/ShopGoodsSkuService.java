package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopGoodsSku;
import com.gxwebsoft.shop.param.ShopGoodsSkuParam;

import java.util.List;

/**
 * 商品sku列表Service
 *
 * @author 科技小王子
 * @since 2025-05-01 09:43:31
 */
public interface ShopGoodsSkuService extends IService<ShopGoodsSku> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopGoodsSku>
     */
    PageResult<ShopGoodsSku> pageRel(ShopGoodsSkuParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopGoodsSku>
     */
    List<ShopGoodsSku> listRel(ShopGoodsSkuParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return ShopGoodsSku
     */
    ShopGoodsSku getByIdRel(Integer id);

}
