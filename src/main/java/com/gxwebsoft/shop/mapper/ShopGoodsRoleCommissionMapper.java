package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopGoodsRoleCommission;
import com.gxwebsoft.shop.param.ShopGoodsRoleCommissionParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品绑定角色的分润金额Mapper
 *
 * @author 科技小王子
 * @since 2025-05-01 09:53:38
 */
public interface ShopGoodsRoleCommissionMapper extends BaseMapper<ShopGoodsRoleCommission> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopGoodsRoleCommission>
     */
    List<ShopGoodsRoleCommission> selectPageRel(@Param("page") IPage<ShopGoodsRoleCommission> page,
                             @Param("param") ShopGoodsRoleCommissionParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopGoodsRoleCommission> selectListRel(@Param("param") ShopGoodsRoleCommissionParam param);

}
