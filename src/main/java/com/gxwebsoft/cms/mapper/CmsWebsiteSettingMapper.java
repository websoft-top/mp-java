package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsWebsiteSetting;
import com.gxwebsoft.cms.param.CmsWebsiteSettingParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 网站设置Mapper
 *
 * @author 科技小王子
 * @since 2025-02-19 01:35:44
 */
public interface CmsWebsiteSettingMapper extends BaseMapper<CmsWebsiteSetting> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsWebsiteSetting>
     */
    List<CmsWebsiteSetting> selectPageRel(@Param("page") IPage<CmsWebsiteSetting> page,
                             @Param("param") CmsWebsiteSettingParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsWebsiteSetting> selectListRel(@Param("param") CmsWebsiteSettingParam param);

}
