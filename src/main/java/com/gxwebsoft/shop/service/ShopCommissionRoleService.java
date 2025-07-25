package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopCommissionRole;
import com.gxwebsoft.shop.param.ShopCommissionRoleParam;

import java.util.List;

/**
 * 分红角色Service
 *
 * @author 科技小王子
 * @since 2025-05-01 10:01:15
 */
public interface ShopCommissionRoleService extends IService<ShopCommissionRole> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopCommissionRole>
     */
    PageResult<ShopCommissionRole> pageRel(ShopCommissionRoleParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopCommissionRole>
     */
    List<ShopCommissionRole> listRel(ShopCommissionRoleParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return ShopCommissionRole
     */
    ShopCommissionRole getByIdRel(Integer id);

}
