package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopMerchantType;
import com.gxwebsoft.shop.param.ShopMerchantTypeParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商户类型Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopMerchantTypeMapper extends BaseMapper<ShopMerchantType> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopMerchantType>
     */
    List<ShopMerchantType> selectPageRel(@Param("page") IPage<ShopMerchantType> page,
                             @Param("param") ShopMerchantTypeParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopMerchantType> selectListRel(@Param("param") ShopMerchantTypeParam param);

}
