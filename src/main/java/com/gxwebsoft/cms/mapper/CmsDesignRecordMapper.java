package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsDesignRecord;
import com.gxwebsoft.cms.param.CmsDesignRecordParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 页面组件表Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsDesignRecordMapper extends BaseMapper<CmsDesignRecord> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsDesignRecord>
     */
    List<CmsDesignRecord> selectPageRel(@Param("page") IPage<CmsDesignRecord> page,
                             @Param("param") CmsDesignRecordParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsDesignRecord> selectListRel(@Param("param") CmsDesignRecordParam param);

}
