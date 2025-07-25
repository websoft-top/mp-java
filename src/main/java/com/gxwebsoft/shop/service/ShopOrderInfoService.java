package com.gxwebsoft.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.shop.entity.ShopOrderInfo;
import com.gxwebsoft.shop.param.ShopOrderInfoParam;

import java.util.List;

/**
 * 场地Service
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderInfoService extends IService<ShopOrderInfo> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<ShopOrderInfo>
     */
    PageResult<ShopOrderInfo> pageRel(ShopOrderInfoParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<ShopOrderInfo>
     */
    List<ShopOrderInfo> listRel(ShopOrderInfoParam param);

    /**
     * 根据id查询
     *
     * @param id 自增ID
     * @return ShopOrderInfo
     */
    ShopOrderInfo getByIdRel(Integer id);

}
