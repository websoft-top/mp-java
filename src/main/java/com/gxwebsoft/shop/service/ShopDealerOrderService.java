package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopDealerOrder;
import com.gxwebsoft.shop.param.ShopDealerOrderParam;

import java.util.List;

/**
 * 分销商订单记录表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerOrderService extends IService<ShopDealerOrder> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopDealerOrder>
     */
    PageResult<ShopDealerOrder> pageRel(ShopDealerOrderParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopDealerOrder>
     */
    List<ShopDealerOrder> listRel(ShopDealerOrderParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return ShopDealerOrder
     */
    ShopDealerOrder getByIdRel(Integer id);

}
