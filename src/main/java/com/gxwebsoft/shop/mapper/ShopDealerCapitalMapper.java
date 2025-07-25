package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopDealerCapital;
import com.gxwebsoft.shop.param.ShopDealerCapitalParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分销商资金明细表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerCapitalMapper extends BaseMapper<ShopDealerCapital> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopDealerCapital>
     */
    List<ShopDealerCapital> selectPageRel(@Param("page") IPage<ShopDealerCapital> page,
                             @Param("param") ShopDealerCapitalParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopDealerCapital> selectListRel(@Param("param") ShopDealerCapitalParam param);

}
