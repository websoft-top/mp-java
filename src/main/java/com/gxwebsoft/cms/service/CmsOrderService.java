package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsOrder;
import com.gxwebsoft.cms.param.CmsOrderParam;

import java.util.List;

/**
 * 订单Service
 *
 * @author 科技小王子
 * @since 2024-11-25 12:14:05
 */
public interface CmsOrderService extends IService<CmsOrder> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsOrder>
     */
    PageResult<CmsOrder> pageRel(CmsOrderParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsOrder>
     */
    List<CmsOrder> listRel(CmsOrderParam param);

    /**
     * 根据id查询
     *
     * @param orderId 订单号
     * @return CmsOrder
     */
    CmsOrder getByIdRel(Integer orderId);

}
