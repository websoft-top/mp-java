package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopExpress;
import com.gxwebsoft.shop.param.ShopExpressParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物流公司Mapper
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
public interface ShopExpressMapper extends BaseMapper<ShopExpress> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopExpress>
     */
    List<ShopExpress> selectPageRel(@Param("page") IPage<ShopExpress> page,
                             @Param("param") ShopExpressParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopExpress> selectListRel(@Param("param") ShopExpressParam param);

}
