package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopMerchantApply;
import com.gxwebsoft.shop.param.ShopMerchantApplyParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商户入驻申请Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopMerchantApplyMapper extends BaseMapper<ShopMerchantApply> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopMerchantApply>
     */
    List<ShopMerchantApply> selectPageRel(@Param("page") IPage<ShopMerchantApply> page,
                             @Param("param") ShopMerchantApplyParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopMerchantApply> selectListRel(@Param("param") ShopMerchantApplyParam param);

}
