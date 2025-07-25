package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopExpressTemplate;
import com.gxwebsoft.shop.param.ShopExpressTemplateParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 运费模板Mapper
 *
 * @author 科技小王子
 * @since 2025-05-01 10:04:21
 */
public interface ShopExpressTemplateMapper extends BaseMapper<ShopExpressTemplate> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopExpressTemplate>
     */
    List<ShopExpressTemplate> selectPageRel(@Param("page") IPage<ShopExpressTemplate> page,
                             @Param("param") ShopExpressTemplateParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopExpressTemplate> selectListRel(@Param("param") ShopExpressTemplateParam param);

}
