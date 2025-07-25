package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsMpFieldMapper;
import com.gxwebsoft.cms.service.CmsMpFieldService;
import com.gxwebsoft.cms.entity.CmsMpField;
import com.gxwebsoft.cms.param.CmsMpFieldParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 小程序配置Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsMpFieldServiceImpl extends ServiceImpl<CmsMpFieldMapper, CmsMpField> implements CmsMpFieldService {

    @Override
    public PageResult<CmsMpField> pageRel(CmsMpFieldParam param) {
        PageParam<CmsMpField, CmsMpFieldParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsMpField> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsMpField> listRel(CmsMpFieldParam param) {
        List<CmsMpField> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsMpField, CmsMpFieldParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsMpField getByIdRel(Integer id) {
        CmsMpFieldParam param = new CmsMpFieldParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
