package com.gxwebsoft.common.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxwebsoft.common.system.entity.Setting;
import com.gxwebsoft.common.system.param.SettingParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统设置Mapper
 *
 * @author WebSoft
 * @since 2022-11-19 13:54:27
 */
public interface SettingMapper extends BaseMapper<Setting> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<Setting>
     */
    List<Setting> selectPageRel(@Param("page") IPage<Setting> page,
                             @Param("param") SettingParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<Setting> selectListRel(@Param("param") SettingParam param);

    @InterceptorIgnore(tenantLine = "true")
    Setting getBySettingKeyIgnore(@Param("param") SettingParam param);
}
