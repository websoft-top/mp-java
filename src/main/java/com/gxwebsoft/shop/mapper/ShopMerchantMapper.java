package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopMerchant;
import com.gxwebsoft.shop.param.ShopMerchantParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商户Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopMerchantMapper extends BaseMapper<ShopMerchant> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopMerchant>
     */
    List<ShopMerchant> selectPageRel(@Param("page") IPage<ShopMerchant> page,
                             @Param("param") ShopMerchantParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopMerchant> selectListRel(@Param("param") ShopMerchantParam param);

}
