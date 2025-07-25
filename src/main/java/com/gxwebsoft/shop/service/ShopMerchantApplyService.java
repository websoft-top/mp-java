package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopMerchantApply;
import com.gxwebsoft.shop.param.ShopMerchantApplyParam;

import java.util.List;

/**
 * 商户入驻申请Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopMerchantApplyService extends IService<ShopMerchantApply> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopMerchantApply>
     */
    PageResult<ShopMerchantApply> pageRel(ShopMerchantApplyParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopMerchantApply>
     */
    List<ShopMerchantApply> listRel(ShopMerchantApplyParam param);

    /**
     * 根据id查询
     *
     * @param applyId ID
     * @return ShopMerchantApply
     */
    ShopMerchantApply getByIdRel(Integer applyId);

}
