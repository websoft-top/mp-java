package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsProductSpecValueMapper;
import com.gxwebsoft.cms.service.CmsProductSpecValueService;
import com.gxwebsoft.cms.entity.CmsProductSpecValue;
import com.gxwebsoft.cms.param.CmsProductSpecValueParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规格值Service实现
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Service
public class CmsProductSpecValueServiceImpl extends ServiceImpl<CmsProductSpecValueMapper, CmsProductSpecValue> implements CmsProductSpecValueService {

    @Override
    public PageResult<CmsProductSpecValue> pageRel(CmsProductSpecValueParam param) {
        PageParam<CmsProductSpecValue, CmsProductSpecValueParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsProductSpecValue> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsProductSpecValue> listRel(CmsProductSpecValueParam param) {
        List<CmsProductSpecValue> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsProductSpecValue, CmsProductSpecValueParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsProductSpecValue getByIdRel(Integer specValueId) {
        CmsProductSpecValueParam param = new CmsProductSpecValueParam();
        param.setSpecValueId(specValueId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
