package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopBrand;
import com.gxwebsoft.shop.param.ShopBrandParam;

import java.util.List;

/**
 * 品牌Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopBrandService extends IService<ShopBrand> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopBrand>
     */
    PageResult<ShopBrand> pageRel(ShopBrandParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopBrand>
     */
    List<ShopBrand> listRel(ShopBrandParam param);

    /**
     * 根据id查询
     *
     * @param brandId ID
     * @return ShopBrand
     */
    ShopBrand getByIdRel(Integer brandId);

}
