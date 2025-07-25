package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsLangLog;
import com.gxwebsoft.cms.entity.CmsLink;
import com.gxwebsoft.cms.param.CmsLangLogParam;
import com.gxwebsoft.cms.param.CmsLinkParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 常用链接Mapper
 *
 * @author 科技小王子
 * @since 2024-09-10 20:47:57
 */
public interface CmsLinkMapper extends BaseMapper<CmsLink> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsLink>
     */
    List<CmsLink> selectPageRel(@Param("page") IPage<CmsLink> page,
                             @Param("param") CmsLinkParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsLink> selectListRel(@Param("param") CmsLinkParam param);


    @InterceptorIgnore(tenantLine = "true")
    List<CmsLink> selectListAllRel(@Param("param") CmsLinkParam param);
}
