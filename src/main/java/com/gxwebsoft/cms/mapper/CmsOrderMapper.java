package com.gxwebsoft.cms.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsNavigation;
import com.gxwebsoft.cms.entity.CmsOrder;
import com.gxwebsoft.cms.param.CmsNavigationParam;
import com.gxwebsoft.cms.param.CmsOrderParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单Mapper
 *
 * @author 科技小王子
 * @since 2024-11-25 12:14:05
 */
public interface CmsOrderMapper extends BaseMapper<CmsOrder> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<CmsOrder>
     */
    List<CmsOrder> selectPageRel(@Param("page") IPage<CmsOrder> page,
                             @Param("param") CmsOrderParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<CmsOrder> selectListRel(@Param("param") CmsOrderParam param);

    @InterceptorIgnore(tenantLine = "true")
    List<CmsOrder> selectListAllRel(@Param("param") CmsOrderParam param);
}
