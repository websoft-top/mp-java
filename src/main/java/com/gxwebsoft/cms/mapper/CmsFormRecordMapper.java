package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsFormRecord;
import com.gxwebsoft.cms.param.CmsFormRecordParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 表单数据记录表Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsFormRecordMapper extends BaseMapper<CmsFormRecord> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsFormRecord>
     */
    List<CmsFormRecord> selectPageRel(@Param("page") IPage<CmsFormRecord> page,
                             @Param("param") CmsFormRecordParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsFormRecord> selectListRel(@Param("param") CmsFormRecordParam param);

}
