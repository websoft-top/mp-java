package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.system.entity.Tenant;
import com.gxwebsoft.common.system.param.TenantParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 租户Mapper
 *
 * @author 科技小王子
 * @since 2023-07-17 17:49:53
 */
public interface TenantMapper extends BaseMapper<Tenant> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<Tenant>
     */
    List<Tenant> selectPageRel(@Param("page") IPage<Tenant> page,
                             @Param("param") TenantParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<Tenant> selectListRel(@Param("param") TenantParam param);

}
