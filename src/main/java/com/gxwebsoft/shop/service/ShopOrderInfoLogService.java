package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopOrderInfoLog;
import com.gxwebsoft.shop.param.ShopOrderInfoLogParam;

import java.util.List;

/**
 * 订单核销Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderInfoLogService extends IService<ShopOrderInfoLog> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopOrderInfoLog>
     */
    PageResult<ShopOrderInfoLog> pageRel(ShopOrderInfoLogParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopOrderInfoLog>
     */
    List<ShopOrderInfoLog> listRel(ShopOrderInfoLogParam param);

    /**
     * 根据id查询
     *
     * @param id 
     * @return ShopOrderInfoLog
     */
    ShopOrderInfoLog getByIdRel(Integer id);

}
