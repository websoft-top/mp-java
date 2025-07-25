package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopUserAddress;
import com.gxwebsoft.shop.param.ShopUserAddressParam;

import java.util.List;

/**
 * 收货地址Service
 *
 * @author 科技小王子
 * @since 2025-07-22 23:06:40
 */
public interface ShopUserAddressService extends IService<ShopUserAddress> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopUserAddress>
     */
    PageResult<ShopUserAddress> pageRel(ShopUserAddressParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopUserAddress>
     */
    List<ShopUserAddress> listRel(ShopUserAddressParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return ShopUserAddress
     */
    ShopUserAddress getByIdRel(Integer id);

}
