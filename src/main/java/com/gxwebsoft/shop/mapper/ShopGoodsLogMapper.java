package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopGoodsLog;
import com.gxwebsoft.shop.param.ShopGoodsLogParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品日志表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopGoodsLogMapper extends BaseMapper<ShopGoodsLog> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopGoodsLog>
     */
    List<ShopGoodsLog> selectPageRel(@Param("page") IPage<ShopGoodsLog> page,
                             @Param("param") ShopGoodsLogParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopGoodsLog> selectListRel(@Param("param") ShopGoodsLogParam param);

}
