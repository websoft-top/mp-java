package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsFormRecordMapper;
import com.gxwebsoft.cms.service.CmsFormRecordService;
import com.gxwebsoft.cms.entity.CmsFormRecord;
import com.gxwebsoft.cms.param.CmsFormRecordParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 表单数据记录表Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsFormRecordServiceImpl extends ServiceImpl<CmsFormRecordMapper, CmsFormRecord> implements CmsFormRecordService {

    @Override
    public PageResult<CmsFormRecord> pageRel(CmsFormRecordParam param) {
        PageParam<CmsFormRecord, CmsFormRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsFormRecord> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsFormRecord> listRel(CmsFormRecordParam param) {
        List<CmsFormRecord> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsFormRecord, CmsFormRecordParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsFormRecord getByIdRel(Integer formRecordId) {
        CmsFormRecordParam param = new CmsFormRecordParam();
        param.setFormRecordId(formRecordId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
