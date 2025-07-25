package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopGoodsRoleCommission;
import com.gxwebsoft.shop.param.ShopGoodsRoleCommissionParam;

import java.util.List;

/**
 * 商品绑定角色的分润金额Service
 *
 * @author 科技小王子
 * @since 2025-05-01 09:53:38
 */
public interface ShopGoodsRoleCommissionService extends IService<ShopGoodsRoleCommission> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopGoodsRoleCommission>
     */
    PageResult<ShopGoodsRoleCommission> pageRel(ShopGoodsRoleCommissionParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopGoodsRoleCommission>
     */
    List<ShopGoodsRoleCommission> listRel(ShopGoodsRoleCommissionParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return ShopGoodsRoleCommission
     */
    ShopGoodsRoleCommission getByIdRel(Integer id);

}
