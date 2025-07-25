package com.gxwebsoft.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.cms.entity.CmsDesignRecord;
import com.gxwebsoft.cms.param.CmsDesignRecordParam;

import java.util.List;

/**
 * 页面组件表Service
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsDesignRecordService extends IService<CmsDesignRecord> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<CmsDesignRecord>
     */
    PageResult<CmsDesignRecord> pageRel(CmsDesignRecordParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<CmsDesignRecord>
     */
    List<CmsDesignRecord> listRel(CmsDesignRecordParam param);

    /**
     * 根据id查询
     *
     * @param id ID
     * @return CmsDesignRecord
     */
    CmsDesignRecord getByIdRel(Integer id);

}
