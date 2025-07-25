package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopGoodsSpec;
import com.gxwebsoft.shop.param.ShopGoodsSpecParam;

import java.util.List;

/**
 * 商品多规格Service
 *
 * @author 科技小王子
 * @since 2025-05-01 09:43:31
 */
public interface ShopGoodsSpecService extends IService<ShopGoodsSpec> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopGoodsSpec>
     */
    PageResult<ShopGoodsSpec> pageRel(ShopGoodsSpecParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopGoodsSpec>
     */
    List<ShopGoodsSpec> listRel(ShopGoodsSpecParam param);

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return ShopGoodsSpec
     */
    ShopGoodsSpec getByIdRel(Integer id);

}
