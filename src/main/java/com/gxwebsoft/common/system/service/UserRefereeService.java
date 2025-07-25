package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.UserReferee;
import com.gxwebsoft.common.system.param.UserRefereeParam;

import java.util.List;

/**
 * 用户推荐关系表Service
 *
 * @author 科技小王子
 * @since 2023-10-07 22:56:36
 */
public interface UserRefereeService extends IService<UserReferee> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<UserReferee>
     */
    PageResult<UserReferee> pageRel(UserRefereeParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<UserReferee>
     */
    List<UserReferee> listRel(UserRefereeParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return UserReferee
     */
    UserReferee getByIdRel(Integer id);

    UserReferee check(Integer dealerId, Integer userId);

    UserReferee getByUserId(Integer userId);
}
