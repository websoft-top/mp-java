package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsProductUrl;
import com.gxwebsoft.cms.param.CmsProductUrlParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 域名Mapper
 *
 * @author 科技小王子
 * @since 2024-09-27 16:03:44
 */
public interface CmsProductUrlMapper extends BaseMapper<CmsProductUrl> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsProductUrl>
     */
    List<CmsProductUrl> selectPageRel(@Param("page") IPage<CmsProductUrl> page,
                             @Param("param") CmsProductUrlParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsProductUrl> selectListRel(@Param("param") CmsProductUrlParam param);

}
