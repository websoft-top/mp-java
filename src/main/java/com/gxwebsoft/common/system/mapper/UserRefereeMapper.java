package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.system.entity.UserReferee;
import com.gxwebsoft.common.system.param.UserRefereeParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户推荐关系表Mapper
 *
 * @author 科技小王子
 * @since 2023-10-07 22:56:36
 */
public interface UserRefereeMapper extends BaseMapper<UserReferee> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<UserReferee>
     */
    List<UserReferee> selectPageRel(@Param("page") IPage<UserReferee> page,
                             @Param("param") UserRefereeParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<UserReferee> selectListRel(@Param("param") UserRefereeParam param);

}
