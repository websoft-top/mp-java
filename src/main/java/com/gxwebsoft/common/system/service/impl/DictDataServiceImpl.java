package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.utils.CommonUtil;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.DictData;
import com.gxwebsoft.common.system.mapper.DictDataMapper;
import com.gxwebsoft.common.system.param.DictDataParam;
import com.gxwebsoft.common.system.service.DictDataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典数据Service实现
 *
 * @author WebSoft
 * @since 2020-03-14 11:29:04
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData>
        implements DictDataService {

    @Override
    public PageResult<DictData> pageRel(DictDataParam param) {
        PageParam<DictData, DictDataParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return new PageResult<>(baseMapper.selectPageRel(page, param), page.getTotal());
    }

    @Override
    public List<DictData> listRel(DictDataParam param) {
        PageParam<DictData, DictDataParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return page.sortRecords(baseMapper.selectListRel(param));
    }

    @Override
    public DictData getByIdRel(Integer dictDataId) {
        DictDataParam param = new DictDataParam();
        param.setDictDataId(dictDataId);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Override
    public DictData getByDictCodeAndName(String dictCode, String dictDataName) {
        List<DictData> list = baseMapper.getByDictCodeAndName(dictCode, dictDataName);
        return CommonUtil.listGetOne(list);
    }

}
