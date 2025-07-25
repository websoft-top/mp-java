package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopOrderExtract;
import com.gxwebsoft.shop.param.ShopOrderExtractParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自提订单联系方式Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopOrderExtractMapper extends BaseMapper<ShopOrderExtract> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopOrderExtract>
     */
    List<ShopOrderExtract> selectPageRel(@Param("page") IPage<ShopOrderExtract> page,
                             @Param("param") ShopOrderExtractParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopOrderExtract> selectListRel(@Param("param") ShopOrderExtractParam param);

}
