package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopUsers;
import com.gxwebsoft.shop.param.ShopUsersParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
public interface ShopUsersMapper extends BaseMapper<ShopUsers> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopUsers>
     */
    List<ShopUsers> selectPageRel(@Param("page") IPage<ShopUsers> page,
                             @Param("param") ShopUsersParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopUsers> selectListRel(@Param("param") ShopUsersParam param);

}
