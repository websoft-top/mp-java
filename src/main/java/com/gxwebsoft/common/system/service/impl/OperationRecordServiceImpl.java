package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.OperationRecord;
import com.gxwebsoft.common.system.mapper.OperationRecordMapper;
import com.gxwebsoft.common.system.param.OperationRecordParam;
import com.gxwebsoft.common.system.service.OperationRecordService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志Service实现
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:02
 */
@Service
public class OperationRecordServiceImpl extends ServiceImpl<OperationRecordMapper, OperationRecord>
        implements OperationRecordService {

    @Override
    public PageResult<OperationRecord> pageRel(OperationRecordParam param) {
        PageParam<OperationRecord, OperationRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return new PageResult<>(baseMapper.selectPageRel(page, param), page.getTotal());
    }

    @Override
    public List<OperationRecord> listRel(OperationRecordParam param) {
        PageParam<OperationRecord, OperationRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(baseMapper.selectListRel(param));
    }

    @Override
    public OperationRecord getByIdRel(Integer id) {
        OperationRecordParam param = new OperationRecordParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Async
    @Override
    public void saveAsync(OperationRecord operationRecord) {
        baseMapper.insert(operationRecord);
    }

}
