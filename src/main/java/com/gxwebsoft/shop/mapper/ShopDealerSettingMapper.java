package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopDealerSetting;
import com.gxwebsoft.shop.param.ShopDealerSettingParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分销商设置表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopDealerSettingMapper extends BaseMapper<ShopDealerSetting> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopDealerSetting>
     */
    List<ShopDealerSetting> selectPageRel(@Param("page") IPage<ShopDealerSetting> page,
                             @Param("param") ShopDealerSettingParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopDealerSetting> selectListRel(@Param("param") ShopDealerSettingParam param);

}
