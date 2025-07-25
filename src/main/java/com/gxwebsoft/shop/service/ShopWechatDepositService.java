package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopWechatDeposit;
import com.gxwebsoft.shop.param.ShopWechatDepositParam;

import java.util.List;

/**
 * 押金Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
public interface ShopWechatDepositService extends IService<ShopWechatDeposit> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopWechatDeposit>
     */
    PageResult<ShopWechatDeposit> pageRel(ShopWechatDepositParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopWechatDeposit>
     */
    List<ShopWechatDeposit> listRel(ShopWechatDepositParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return ShopWechatDeposit
     */
    ShopWechatDeposit getByIdRel(Integer id);

}
