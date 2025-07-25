package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopSpec;
import com.gxwebsoft.shop.param.ShopSpecParam;

import java.util.List;

/**
 * 规格Service
 *
 * @author 科技小王子
 * @since 2025-05-01 09:44:00
 */
public interface ShopSpecService extends IService<ShopSpec> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopSpec>
     */
    PageResult<ShopSpec> pageRel(ShopSpecParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopSpec>
     */
    List<ShopSpec> listRel(ShopSpecParam param);

    /**
     * 根据id查询
     *
     * @param specId 规格ID
     * @return ShopSpec
     */
    ShopSpec getByIdRel(Integer specId);

}
