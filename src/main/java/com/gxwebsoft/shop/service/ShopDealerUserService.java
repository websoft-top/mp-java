package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopDealerUser;
import com.gxwebsoft.shop.param.ShopDealerUserParam;

import java.util.List;

/**
 * 分销商用户记录表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerUserService extends IService<ShopDealerUser> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopDealerUser>
     */
    PageResult<ShopDealerUser> pageRel(ShopDealerUserParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopDealerUser>
     */
    List<ShopDealerUser> listRel(ShopDealerUserParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return ShopDealerUser
     */
    ShopDealerUser getByIdRel(Integer id);

}
