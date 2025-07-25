package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopUserBalanceLog;
import com.gxwebsoft.shop.param.ShopUserBalanceLogParam;

import java.util.List;

/**
 * 用户余额变动明细表Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
public interface ShopUserBalanceLogService extends IService<ShopUserBalanceLog> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopUserBalanceLog>
     */
    PageResult<ShopUserBalanceLog> pageRel(ShopUserBalanceLogParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopUserBalanceLog>
     */
    List<ShopUserBalanceLog> listRel(ShopUserBalanceLogParam param);

    /**
     * 根据id查询
     *
     * @param logId 主键ID
     * @return ShopUserBalanceLog
     */
    ShopUserBalanceLog getByIdRel(Integer logId);

}
