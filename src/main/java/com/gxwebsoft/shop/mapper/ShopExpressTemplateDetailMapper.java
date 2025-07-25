package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopExpressTemplateDetail;
import com.gxwebsoft.shop.param.ShopExpressTemplateDetailParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 运费模板Mapper
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
public interface ShopExpressTemplateDetailMapper extends BaseMapper<ShopExpressTemplateDetail> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopExpressTemplateDetail>
     */
    List<ShopExpressTemplateDetail> selectPageRel(@Param("page") IPage<ShopExpressTemplateDetail> page,
                             @Param("param") ShopExpressTemplateDetailParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopExpressTemplateDetail> selectListRel(@Param("param") ShopExpressTemplateDetailParam param);

}
