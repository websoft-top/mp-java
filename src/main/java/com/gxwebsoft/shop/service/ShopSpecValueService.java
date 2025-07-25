package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopSpecValue;
import com.gxwebsoft.shop.param.ShopSpecValueParam;

import java.util.List;

/**
 * 规格值Service
 *
 * @author 科技小王子
 * @since 2025-05-01 09:44:00
 */
public interface ShopSpecValueService extends IService<ShopSpecValue> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopSpecValue>
     */
    PageResult<ShopSpecValue> pageRel(ShopSpecValueParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopSpecValue>
     */
    List<ShopSpecValue> listRel(ShopSpecValueParam param);

    /**
     * 根据id查询
     *
     * @param specValueId 规格值ID
     * @return ShopSpecValue
     */
    ShopSpecValue getByIdRel(Integer specValueId);

}
