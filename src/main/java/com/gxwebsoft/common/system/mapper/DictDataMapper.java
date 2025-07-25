package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.system.entity.DictData;
import com.gxwebsoft.common.system.param.DictDataParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典数据Mapper
 *
 * @author WebSoft
 * @since 2020-03-14 11:29:04
 */
public interface DictDataMapper extends BaseMapper<DictData> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<DictData>
     */
    List<DictData> selectPageRel(@Param("page") IPage<DictData> page,
                                       @Param("param") DictDataParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<DictData>
     */
    List<DictData> selectListRel(@Param("param") DictDataParam param);

    /**
     * 根据dictCode和dictDataName查询
     *
     * @param dictCode     字典标识
     * @param dictDataName 字典项名称
     * @return List<DictData>
     */
    List<DictData> getByDictCodeAndName(@Param("dictCode") String dictCode,
                                              @Param("dictDataName") String dictDataName);

}
