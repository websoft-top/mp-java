package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopUserBalanceLog;
import com.gxwebsoft.shop.param.ShopUserBalanceLogParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户余额变动明细表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
public interface ShopUserBalanceLogMapper extends BaseMapper<ShopUserBalanceLog> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopUserBalanceLog>
     */
    List<ShopUserBalanceLog> selectPageRel(@Param("page") IPage<ShopUserBalanceLog> page,
                             @Param("param") ShopUserBalanceLogParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopUserBalanceLog> selectListRel(@Param("param") ShopUserBalanceLogParam param);

}
