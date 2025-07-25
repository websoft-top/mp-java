package com.gxwebsoft.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.shop.entity.ShopChatMessage;
import com.gxwebsoft.shop.param.ShopChatMessageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 聊天消息表Mapper
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
public interface ShopChatMessageMapper extends BaseMapper<ShopChatMessage> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<ShopChatMessage>
     */
    List<ShopChatMessage> selectPageRel(@Param("page") IPage<ShopChatMessage> page,
                             @Param("param") ShopChatMessageParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<ShopChatMessage> selectListRel(@Param("param") ShopChatMessageParam param);

}
