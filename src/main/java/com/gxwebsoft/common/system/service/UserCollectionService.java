package com.gxwebsoft.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.UserCollection;
import com.gxwebsoft.common.system.param.UserCollectionParam;

import java.util.List;

/**
 * 我的收藏Service
 *
 * @author 科技小王子
 * @since 2024-04-28 18:08:32
 */
public interface UserCollectionService extends IService<UserCollection> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<UserCollection>
     */
    PageResult<UserCollection> pageRel(UserCollectionParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<UserCollection>
     */
    List<UserCollection> listRel(UserCollectionParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return UserCollection
     */
    UserCollection getByIdRel(Integer id);

}
