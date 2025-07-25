package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopDealerApply;
import com.gxwebsoft.shop.param.ShopDealerApplyParam;

import java.util.List;

/**
 * 分销商申请记录表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerApplyService extends IService<ShopDealerApply> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopDealerApply>
     */
    PageResult<ShopDealerApply> pageRel(ShopDealerApplyParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopDealerApply>
     */
    List<ShopDealerApply> listRel(ShopDealerApplyParam param);

    /**
     * 根据id查询
     *
     * @param applyId 主键ID
     * @return ShopDealerApply
     */
    ShopDealerApply getByIdRel(Integer applyId);

}
