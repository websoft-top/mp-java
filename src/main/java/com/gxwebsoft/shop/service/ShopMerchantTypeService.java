package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopMerchantType;
import com.gxwebsoft.shop.param.ShopMerchantTypeParam;

import java.util.List;

/**
 * 商户类型Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopMerchantTypeService extends IService<ShopMerchantType> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopMerchantType>
     */
    PageResult<ShopMerchantType> pageRel(ShopMerchantTypeParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopMerchantType>
     */
    List<ShopMerchantType> listRel(ShopMerchantTypeParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return ShopMerchantType
     */
    ShopMerchantType getByIdRel(Integer id);

}
