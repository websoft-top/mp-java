package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopCart;
import com.gxwebsoft.shop.param.ShopCartParam;

import java.util.List;

/**
 * 购物车Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopCartService extends IService<ShopCart> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopCart>
     */
    PageResult<ShopCart> pageRel(ShopCartParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopCart>
     */
    List<ShopCart> listRel(ShopCartParam param);

    /**
     * 根据id查询
     *
     * @param id 购物车表ID
     * @return ShopCart
     */
    ShopCart getByIdRel(Long id);

}
