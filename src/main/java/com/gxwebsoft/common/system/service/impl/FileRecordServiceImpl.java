package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.utils.CommonUtil;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.FileRecord;
import com.gxwebsoft.common.system.mapper.FileRecordMapper;
import com.gxwebsoft.common.system.param.FileRecordParam;
import com.gxwebsoft.common.system.service.FileRecordService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * 文件上传记录Service实现
 *
 * @author WebSoft
 * @since 2021-08-30 11:21:01
 */
@Service
public class FileRecordServiceImpl extends ServiceImpl<FileRecordMapper, FileRecord> implements FileRecordService {

    @Override
    public PageResult<FileRecord> pageRel(FileRecordParam param) {
        PageParam<FileRecord, FileRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return new PageResult<>(baseMapper.selectPageRel(page, param), page.getTotal());
    }

    @Override
    public List<FileRecord> listRel(FileRecordParam param) {
        PageParam<FileRecord, FileRecordParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        return page.sortRecords(baseMapper.selectListRel(param));
    }

    @Override
    public FileRecord getByIdRel(Integer id) {
        FileRecordParam param = new FileRecordParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Override
    public FileRecord getByIdPath(String path) {
        return CommonUtil.listGetOne(baseMapper.getByIdPath(path));
    }

    @Async
    @Override
    public void deleteFileAsync(List<File> files) {
        for (File file : files) {
            try {
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
