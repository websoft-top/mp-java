package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.system.entity.UserCollection;
import com.gxwebsoft.common.system.param.UserCollectionParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 我的收藏Mapper
 *
 * @author 科技小王子
 * @since 2024-04-28 18:08:32
 */
public interface UserCollectionMapper extends BaseMapper<UserCollection> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<UserCollection>
     */
    List<UserCollection> selectPageRel(@Param("page") IPage<UserCollection> page,
                             @Param("param") UserCollectionParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<UserCollection> selectListRel(@Param("param") UserCollectionParam param);

}
