package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsModelMapper;
import com.gxwebsoft.cms.service.CmsModelService;
import com.gxwebsoft.cms.entity.CmsModel;
import com.gxwebsoft.cms.param.CmsModelParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模型Service实现
 *
 * @author 科技小王子
 * @since 2024-11-26 15:44:53
 */
@Service
public class CmsModelServiceImpl extends ServiceImpl<CmsModelMapper, CmsModel> implements CmsModelService {

    @Override
    public PageResult<CmsModel> pageRel(CmsModelParam param) {
        PageParam<CmsModel, CmsModelParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time asc");
        List<CmsModel> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsModel> listRel(CmsModelParam param) {
        List<CmsModel> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsModel, CmsModelParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time asc");
        return page.sortRecords(list);
    }

    @Override
    public CmsModel getByIdRel(Integer modelId) {
        CmsModelParam param = new CmsModelParam();
        param.setModelId(modelId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
