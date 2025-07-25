package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.*;
import com.gxwebsoft.common.system.entity.DictData;
import com.gxwebsoft.common.system.param.DictDataParam;

import java.util.List;

/**
 * 字典数据Service
 *
 * @author WebSoft
 * @since 2020-03-14 11:29:04
 */
public interface DictDataService extends IService<DictData> {

    /**
     * 关联分页查询
     *
     * @param param 查询参数
     * @return PageResult<DictData>
     */
    PageResult<DictData> pageRel(DictDataParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<DictData>
     */
    List<DictData> listRel(DictDataParam param);

    /**
     * 根据id查询
     *
     * @param dictDataId 字典数据id
     * @return DictData
     */
    DictData getByIdRel(Integer dictDataId);

    /**
     * 根据dictCode和dictDataName查询
     *
     * @param dictCode     字典标识
     * @param dictDataName 字典项名称
     * @return DictData
     */
    DictData getByDictCodeAndName(String dictCode, String dictDataName);

}
