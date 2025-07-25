package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsProductMapper;
import com.gxwebsoft.cms.service.CmsProductService;
import com.gxwebsoft.cms.entity.CmsProduct;
import com.gxwebsoft.cms.param.CmsProductParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品Service实现
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Service
public class CmsProductServiceImpl extends ServiceImpl<CmsProductMapper, CmsProduct> implements CmsProductService {

    @Override
    public PageResult<CmsProduct> pageRel(CmsProductParam param) {
        PageParam<CmsProduct, CmsProductParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc,create_time desc");
        List<CmsProduct> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsProduct> listRel(CmsProductParam param) {
        List<CmsProduct> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsProduct, CmsProductParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc,create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsProduct getByIdRel(Integer productId) {
        CmsProductParam param = new CmsProductParam();
        param.setProductId(productId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
