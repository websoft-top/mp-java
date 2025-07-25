package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.cms.entity.CmsWebsite;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.param.UserParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:14
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<User>
     */
    List<User> selectPageRel(@Param("page") IPage<User> page,
                             @Param("param") UserParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<User> selectListRel(@Param("param") UserParam param);

    /**
     * 根据账号查询
     *
     * @param username 账号
     * @param tenantId 租户id
     * @return User
     */
    @InterceptorIgnore(tenantLine = "true")
    User selectByUsername(@Param("username") String username, @Param("tenantId") Integer tenantId);

    @InterceptorIgnore(tenantLine = "true")
    List<User> getOne(@Param("param") UserParam param);

    List<User> selectListStatisticsRel(@Param("param") UserParam param);

    @InterceptorIgnore(tenantLine = "true")
    void updateByUserId(@Param("param") User param);

    @InterceptorIgnore(tenantLine = "true")
    List<User> pageAdminByPhone(@Param("param") UserParam param);

    @InterceptorIgnore(tenantLine = "true")
    List<User> listByAlert();
}
