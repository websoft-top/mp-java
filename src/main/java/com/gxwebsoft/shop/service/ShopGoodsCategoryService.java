package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopGoodsCategory;
import com.gxwebsoft.shop.param.ShopGoodsCategoryParam;

import java.util.List;

/**
 * 商品分类Service
 *
 * @author 科技小王子
 * @since 2025-05-01 00:36:45
 */
public interface ShopGoodsCategoryService extends IService<ShopGoodsCategory> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopGoodsCategory>
     */
    PageResult<ShopGoodsCategory> pageRel(ShopGoodsCategoryParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopGoodsCategory>
     */
    List<ShopGoodsCategory> listRel(ShopGoodsCategoryParam param);

    /**
     * 根据id查询
     *
     * @param categoryId 商品分类ID
     * @return ShopGoodsCategory
     */
    ShopGoodsCategory getByIdRel(Integer categoryId);

}
