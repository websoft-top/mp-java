package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsProductSpecMapper;
import com.gxwebsoft.cms.service.CmsProductSpecService;
import com.gxwebsoft.cms.entity.CmsProductSpec;
import com.gxwebsoft.cms.param.CmsProductSpecParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规格Service实现
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
@Service
public class CmsProductSpecServiceImpl extends ServiceImpl<CmsProductSpecMapper, CmsProductSpec> implements CmsProductSpecService {

    @Override
    public PageResult<CmsProductSpec> pageRel(CmsProductSpecParam param) {
        PageParam<CmsProductSpec, CmsProductSpecParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsProductSpec> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsProductSpec> listRel(CmsProductSpecParam param) {
        List<CmsProductSpec> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsProductSpec, CmsProductSpecParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsProductSpec getByIdRel(Integer specId) {
        CmsProductSpecParam param = new CmsProductSpecParam();
        param.setSpecId(specId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
