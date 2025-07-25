package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopMerchantAccount;
import com.gxwebsoft.shop.param.ShopMerchantAccountParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商户账号Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopMerchantAccountMapper extends BaseMapper<ShopMerchantAccount> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopMerchantAccount>
     */
    List<ShopMerchantAccount> selectPageRel(@Param("page") IPage<ShopMerchantAccount> page,
                             @Param("param") ShopMerchantAccountParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopMerchantAccount> selectListRel(@Param("param") ShopMerchantAccountParam param);

}
