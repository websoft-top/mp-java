package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopUserAddress;
import com.gxwebsoft.shop.param.ShopUserAddressParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收货地址Mapper
 *
 * @author 科技小王子
 * @since 2025-07-22 23:06:40
 */
public interface ShopUserAddressMapper extends BaseMapper<ShopUserAddress> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopUserAddress>
     */
    List<ShopUserAddress> selectPageRel(@Param("page") IPage<ShopUserAddress> page,
                             @Param("param") ShopUserAddressParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopUserAddress> selectListRel(@Param("param") ShopUserAddressParam param);

}
