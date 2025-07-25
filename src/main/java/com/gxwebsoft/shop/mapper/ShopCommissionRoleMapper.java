package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopCommissionRole;
import com.gxwebsoft.shop.param.ShopCommissionRoleParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分红角色Mapper
 *
 * @author 科技小王子
 * @since 2025-05-01 10:01:15
 */
public interface ShopCommissionRoleMapper extends BaseMapper<ShopCommissionRole> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopCommissionRole>
     */
    List<ShopCommissionRole> selectPageRel(@Param("page") IPage<ShopCommissionRole> page,
                             @Param("param") ShopCommissionRoleParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopCommissionRole> selectListRel(@Param("param") ShopCommissionRoleParam param);

}
