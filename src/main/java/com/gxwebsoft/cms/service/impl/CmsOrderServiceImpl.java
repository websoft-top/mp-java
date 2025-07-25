package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsOrderMapper;
import com.gxwebsoft.cms.service.CmsOrderService;
import com.gxwebsoft.cms.entity.CmsOrder;
import com.gxwebsoft.cms.param.CmsOrderParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单Service实现
 *
 * @author 科技小王子
 * @since 2024-11-25 12:14:05
 */
@Service
public class CmsOrderServiceImpl extends ServiceImpl<CmsOrderMapper, CmsOrder> implements CmsOrderService {

    @Override
    public PageResult<CmsOrder> pageRel(CmsOrderParam param) {
        PageParam<CmsOrder, CmsOrderParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<CmsOrder> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsOrder> listRel(CmsOrderParam param) {
        List<CmsOrder> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsOrder, CmsOrderParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsOrder getByIdRel(Integer orderId) {
        CmsOrderParam param = new CmsOrderParam();
        param.setOrderId(orderId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
