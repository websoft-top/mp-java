package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopDealerApply;
import com.gxwebsoft.shop.param.ShopDealerApplyParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分销商申请记录表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerApplyMapper extends BaseMapper<ShopDealerApply> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopDealerApply>
     */
    List<ShopDealerApply> selectPageRel(@Param("page") IPage<ShopDealerApply> page,
                             @Param("param") ShopDealerApplyParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopDealerApply> selectListRel(@Param("param") ShopDealerApplyParam param);

}
