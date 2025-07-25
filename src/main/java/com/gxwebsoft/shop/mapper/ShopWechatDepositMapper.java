package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopWechatDeposit;
import com.gxwebsoft.shop.param.ShopWechatDepositParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 押金Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:13
 */
public interface ShopWechatDepositMapper extends BaseMapper<ShopWechatDeposit> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopWechatDeposit>
     */
    List<ShopWechatDeposit> selectPageRel(@Param("page") IPage<ShopWechatDeposit> page,
                             @Param("param") ShopWechatDepositParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopWechatDeposit> selectListRel(@Param("param") ShopWechatDepositParam param);

}
