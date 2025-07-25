package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopDealerUser;
import com.gxwebsoft.shop.param.ShopDealerUserParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分销商用户记录表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerUserMapper extends BaseMapper<ShopDealerUser> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopDealerUser>
     */
    List<ShopDealerUser> selectPageRel(@Param("page") IPage<ShopDealerUser> page,
                             @Param("param") ShopDealerUserParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopDealerUser> selectListRel(@Param("param") ShopDealerUserParam param);

}
