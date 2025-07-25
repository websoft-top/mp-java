package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopCategory;
import com.gxwebsoft.shop.param.ShopCategoryParam;

import java.util.List;

/**
 * 商品分类Service
 *
 * @author 科技小王子
 * @since 2025-04-24 20:52:13
 */
public interface ShopCategoryService extends IService<ShopCategory> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopCategory>
     */
    PageResult<ShopCategory> pageRel(ShopCategoryParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopCategory>
     */
    List<ShopCategory> listRel(ShopCategoryParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return ShopCategory
     */
    ShopCategory getByIdRel(Integer id);

}
