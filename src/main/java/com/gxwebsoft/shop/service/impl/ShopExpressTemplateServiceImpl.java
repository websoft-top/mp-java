package com.gxwebsoft.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.shop.mapper.ShopExpressTemplateMapper;
import com.gxwebsoft.shop.service.ShopExpressTemplateService;
import com.gxwebsoft.shop.entity.ShopExpressTemplate;
import com.gxwebsoft.shop.param.ShopExpressTemplateParam;
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
public class ShopExpressTemplateServiceImpl extends ServiceImpl<ShopExpressTemplateMapper, ShopExpressTemplate> implements ShopExpressTemplateService {

    @Override
    public PageResult<ShopExpressTemplate> pageRel(ShopExpressTemplateParam param) {
        PageParam<ShopExpressTemplate, ShopExpressTemplateParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopExpressTemplate> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopExpressTemplate> listRel(ShopExpressTemplateParam param) {
        List<ShopExpressTemplate> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopExpressTemplate, ShopExpressTemplateParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopExpressTemplate getByIdRel(Integer id) {
        ShopExpressTemplateParam param = new ShopExpressTemplateParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
