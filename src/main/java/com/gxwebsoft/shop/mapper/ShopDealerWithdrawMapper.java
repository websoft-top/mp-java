package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopDealerWithdraw;
import com.gxwebsoft.shop.param.ShopDealerWithdrawParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分销商提现明细表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerWithdrawMapper extends BaseMapper<ShopDealerWithdraw> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopDealerWithdraw>
     */
    List<ShopDealerWithdraw> selectPageRel(@Param("page") IPage<ShopDealerWithdraw> page,
                             @Param("param") ShopDealerWithdrawParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopDealerWithdraw> selectListRel(@Param("param") ShopDealerWithdrawParam param);

}
