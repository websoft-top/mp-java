package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopExpressTemplateDetailMapper;
import com.gxwebsoft.shop.service.ShopExpressTemplateDetailService;
import com.gxwebsoft.shop.entity.ShopExpressTemplateDetail;
import com.gxwebsoft.shop.param.ShopExpressTemplateDetailParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运费模板Service实现
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
@Service
public class ShopExpressTemplateDetailServiceImpl extends ServiceImpl<ShopExpressTemplateDetailMapper, ShopExpressTemplateDetail> implements ShopExpressTemplateDetailService {

    @Override
    public PageResult<ShopExpressTemplateDetail> pageRel(ShopExpressTemplateDetailParam param) {
        PageParam<ShopExpressTemplateDetail, ShopExpressTemplateDetailParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopExpressTemplateDetail> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopExpressTemplateDetail> listRel(ShopExpressTemplateDetailParam param) {
        List<ShopExpressTemplateDetail> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopExpressTemplateDetail, ShopExpressTemplateDetailParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopExpressTemplateDetail getByIdRel(Integer id) {
        ShopExpressTemplateDetailParam param = new ShopExpressTemplateDetailParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
