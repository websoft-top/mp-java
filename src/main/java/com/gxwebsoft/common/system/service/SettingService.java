package com.gxwebsoft.common.system.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Setting;
import com.gxwebsoft.common.system.param.SettingParam;
import com.wechat.pay.java.core.Config;

import java.util.List;

/**
 * 系统设置Service
 *
 * @author WebSoft
 * @since 2022-11-19 13:54:27
 */
public interface SettingService extends IService<Setting> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<Setting>
     */
    PageResult<Setting> pageRel(SettingParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<Setting>
     */
    List<Setting> listRel(SettingParam param);

    /**
     * 根据id查询
     *
     * @param settingId id
     * @return Setting
     */
    Setting getByIdRel(Integer settingId);

    /**
     * 通过key获取设置内容
     * @param key key
     * @return Setting
     */
    JSONObject getBySettingKey(String key);

    Setting getData(String settingKey);

    JSONObject getCache(String key);

    void initConfig(Setting setting);

    Config getConfig(Integer tenantId);

}
