package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopDealerReferee;
import com.gxwebsoft.shop.param.ShopDealerRefereeParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分销商推荐关系表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerRefereeMapper extends BaseMapper<ShopDealerReferee> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopDealerReferee>
     */
    List<ShopDealerReferee> selectPageRel(@Param("page") IPage<ShopDealerReferee> page,
                             @Param("param") ShopDealerRefereeParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopDealerReferee> selectListRel(@Param("param") ShopDealerRefereeParam param);

}
