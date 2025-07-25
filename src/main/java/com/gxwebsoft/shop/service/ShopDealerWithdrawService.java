package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopDealerWithdraw;
import com.gxwebsoft.shop.param.ShopDealerWithdrawParam;

import java.util.List;

/**
 * 分销商提现明细表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerWithdrawService extends IService<ShopDealerWithdraw> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopDealerWithdraw>
     */
    PageResult<ShopDealerWithdraw> pageRel(ShopDealerWithdrawParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopDealerWithdraw>
     */
    List<ShopDealerWithdraw> listRel(ShopDealerWithdrawParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return ShopDealerWithdraw
     */
    ShopDealerWithdraw getByIdRel(Integer id);

}
