package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopDealerOrder;
import com.gxwebsoft.shop.param.ShopDealerOrderParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分销商订单记录表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerOrderMapper extends BaseMapper<ShopDealerOrder> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopDealerOrder>
     */
    List<ShopDealerOrder> selectPageRel(@Param("page") IPage<ShopDealerOrder> page,
                             @Param("param") ShopDealerOrderParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopDealerOrder> selectListRel(@Param("param") ShopDealerOrderParam param);

}
