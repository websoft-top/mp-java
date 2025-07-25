package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsMpMapper;
import com.gxwebsoft.cms.service.CmsMpService;
import com.gxwebsoft.cms.entity.CmsMp;
import com.gxwebsoft.cms.param.CmsMpParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 小程序信息Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsMpServiceImpl extends ServiceImpl<CmsMpMapper, CmsMp> implements CmsMpService {

    @Override
    public PageResult<CmsMp> pageRel(CmsMpParam param) {
        PageParam<CmsMp, CmsMpParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsMp> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsMp> listRel(CmsMpParam param) {
        List<CmsMp> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsMp, CmsMpParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsMp getByIdRel(Integer mpId) {
        CmsMpParam param = new CmsMpParam();
        param.setMpId(mpId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
