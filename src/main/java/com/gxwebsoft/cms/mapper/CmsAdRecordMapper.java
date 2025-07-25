package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsAdRecord;
import com.gxwebsoft.cms.param.CmsAdRecordParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 广告图片Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsAdRecordMapper extends BaseMapper<CmsAdRecord> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsAdRecord>
     */
    List<CmsAdRecord> selectPageRel(@Param("page") IPage<CmsAdRecord> page,
                             @Param("param") CmsAdRecordParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsAdRecord> selectListRel(@Param("param") CmsAdRecordParam param);

}
