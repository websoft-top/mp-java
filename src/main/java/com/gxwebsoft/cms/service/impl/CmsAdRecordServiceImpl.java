package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsAdRecordMapper;
import com.gxwebsoft.cms.service.CmsAdRecordService;
import com.gxwebsoft.cms.entity.CmsAdRecord;
import com.gxwebsoft.cms.param.CmsAdRecordParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告图片Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsAdRecordServiceImpl extends ServiceImpl<CmsAdRecordMapper, CmsAdRecord> implements CmsAdRecordService {

    @Override
    public PageResult<CmsAdRecord> pageRel(CmsAdRecordParam param) {
        PageParam<CmsAdRecord, CmsAdRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsAdRecord> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsAdRecord> listRel(CmsAdRecordParam param) {
        List<CmsAdRecord> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsAdRecord, CmsAdRecordParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsAdRecord getByIdRel(Integer adRecordId) {
        CmsAdRecordParam param = new CmsAdRecordParam();
        param.setAdRecordId(adRecordId);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
