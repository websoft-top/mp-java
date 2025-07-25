package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopUsers;
import com.gxwebsoft.shop.param.ShopUsersParam;

import java.util.List;

/**
 * Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
public interface ShopUsersService extends IService<ShopUsers> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopUsers>
     */
    PageResult<ShopUsers> pageRel(ShopUsersParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopUsers>
     */
    List<ShopUsers> listRel(ShopUsersParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return ShopUsers
     */
    ShopUsers getByIdRel(Integer id);

}
