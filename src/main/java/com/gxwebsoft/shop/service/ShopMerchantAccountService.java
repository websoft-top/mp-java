package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopMerchantAccount;
import com.gxwebsoft.shop.param.ShopMerchantAccountParam;

import java.util.List;

/**
 * 商户账号Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopMerchantAccountService extends IService<ShopMerchantAccount> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopMerchantAccount>
     */
    PageResult<ShopMerchantAccount> pageRel(ShopMerchantAccountParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopMerchantAccount>
     */
    List<ShopMerchantAccount> listRel(ShopMerchantAccountParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return ShopMerchantAccount
     */
    ShopMerchantAccount getByIdRel(Integer id);

}
