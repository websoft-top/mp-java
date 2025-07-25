package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopUserReferee;
import com.gxwebsoft.shop.param.ShopUserRefereeParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户推荐关系表Mapper
 *
 * @author 科技小王子
 * @since 2025-03-05 17:05:28
 */
public interface ShopUserRefereeMapper extends BaseMapper<ShopUserReferee> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopUserReferee>
     */
    List<ShopUserReferee> selectPageRel(@Param("page") IPage<ShopUserReferee> page,
                             @Param("param") ShopUserRefereeParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopUserReferee> selectListRel(@Param("param") ShopUserRefereeParam param);

}
