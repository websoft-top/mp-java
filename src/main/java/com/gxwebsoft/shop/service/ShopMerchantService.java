package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopMerchant;
import com.gxwebsoft.shop.param.ShopMerchantParam;

import java.util.List;

/**
 * 商户Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopMerchantService extends IService<ShopMerchant> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopMerchant>
     */
    PageResult<ShopMerchant> pageRel(ShopMerchantParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopMerchant>
     */
    List<ShopMerchant> listRel(ShopMerchantParam param);

    /**
     * 根据id查询
     *
     * @param merchantId ID
     * @return ShopMerchant
     */
    ShopMerchant getByIdRel(Long merchantId);

}
