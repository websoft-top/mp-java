package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsComponentsMapper;
import com.gxwebsoft.cms.service.CmsComponentsService;
import com.gxwebsoft.cms.entity.CmsComponents;
import com.gxwebsoft.cms.param.CmsComponentsParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组件Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsComponentsServiceImpl extends ServiceImpl<CmsComponentsMapper, CmsComponents> implements CmsComponentsService {

    @Override
    public PageResult<CmsComponents> pageRel(CmsComponentsParam param) {
        PageParam<CmsComponents, CmsComponentsParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsComponents> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsComponents> listRel(CmsComponentsParam param) {
        List<CmsComponents> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsComponents, CmsComponentsParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsComponents getByIdRel(Integer id) {
        CmsComponentsParam param = new CmsComponentsParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
