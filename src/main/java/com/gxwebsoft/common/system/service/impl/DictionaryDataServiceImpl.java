package com.gxwebsoft.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.utils.CommonUtil;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.DictionaryData;
import com.gxwebsoft.common.system.mapper.DictionaryDataMapper;
import com.gxwebsoft.common.system.param.DictionaryDataParam;
import com.gxwebsoft.common.system.service.DictionaryDataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典数据Service实现
 *
 * @author WebSoft
 * @since 2020-03-14 11:29:04
 */
@Service
public class DictionaryDataServiceImpl extends ServiceImpl<DictionaryDataMapper, DictionaryData>
        implements DictionaryDataService {

    @Override
    public PageResult<DictionaryData> pageRel(DictionaryDataParam param) {
        PageParam<DictionaryData, DictionaryDataParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return new PageResult<>(baseMapper.selectPageRel(page, param), page.getTotal());
    }

    @Override
    public List<DictionaryData> listRel(DictionaryDataParam param) {
        PageParam<DictionaryData, DictionaryDataParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return page.sortRecords(baseMapper.selectListRel(param));
    }

    @Override
    public DictionaryData getByIdRel(Integer dictDataId) {
        DictionaryDataParam param = new DictionaryDataParam();
        param.setDictDataId(dictDataId);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Override
    public DictionaryData getByDictCodeAndName(String dictCode, String dictDataName) {
        List<DictionaryData> list = baseMapper.getByDictCodeAndName(dictCode, dictDataName);
        return CommonUtil.listGetOne(list);
    }

}
