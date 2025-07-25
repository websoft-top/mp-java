package com.gxwebsoft.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.cms.mapper.CmsDesignRecordMapper;
import com.gxwebsoft.cms.service.CmsDesignRecordService;
import com.gxwebsoft.cms.entity.CmsDesignRecord;
import com.gxwebsoft.cms.param.CmsDesignRecordParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 页面组件表Service实现
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
@Service
public class CmsDesignRecordServiceImpl extends ServiceImpl<CmsDesignRecordMapper, CmsDesignRecord> implements CmsDesignRecordService {

    @Override
    public PageResult<CmsDesignRecord> pageRel(CmsDesignRecordParam param) {
        PageParam<CmsDesignRecord, CmsDesignRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<CmsDesignRecord> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<CmsDesignRecord> listRel(CmsDesignRecordParam param) {
        List<CmsDesignRecord> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<CmsDesignRecord, CmsDesignRecordParam> page = new PageParam<>();
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public CmsDesignRecord getByIdRel(Integer id) {
        CmsDesignRecordParam param = new CmsDesignRecordParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
