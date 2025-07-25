package com.gxwebsoft.common.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.config.ConfigProperties;
import com.gxwebsoft.common.core.exception.BusinessException;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.system.entity.Setting;
import com.gxwebsoft.common.system.mapper.SettingMapper;
import com.gxwebsoft.common.system.param.SettingParam;
import com.gxwebsoft.common.system.service.SettingService;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAConfig;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统设置Service实现
 *
 * @author WebSoft
 * @since 2022-11-19 13:54:27
 */
@Service
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements SettingService {
    // 本地缓存
    public static Map<String, Config> configMap = new HashMap<>();
    public static JsapiService service = null;
    public static Config config = null;
    @Resource
    private ConfigProperties pathConfig;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public PageResult<Setting> pageRel(SettingParam param) {
        PageParam<Setting, SettingParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<Setting> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<Setting> listRel(SettingParam param) {
        List<Setting> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<Setting, SettingParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public Setting getByIdRel(Integer settingId) {
        SettingParam param = new SettingParam();
        param.setSettingId(settingId);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Override
    public JSONObject getBySettingKey(String key) {
      Setting setting = this.getOne(new QueryWrapper<Setting>().eq("setting_key", key), false);
      if(setting == null){
        if ("mp-weixin".equals(key)) {
          throw new BusinessException("小程序未配置");
        }
        if ("payment".equals(key)) {
          throw new BusinessException("支付未配置");
        }
        if ("sms".equals(key)) {
          throw new BusinessException("短信未配置");
        }
        if ("wx-work".equals(key)){
          throw new BusinessException("企业微信未配置");
        }
        if ("setting".equals(key)) {
          throw new BusinessException("基本信息未配置");
        }
        if ("wx-official".equals(key)) {
          throw new BusinessException("微信公众号未配置");
        }
        if ("printer".equals(key)) {
          throw new BusinessException("打印机未配置");
        }
      }
      return JSON.parseObject(setting.getContent());
    }

    @Override
    public Setting getData(String settingKey) {
      return query().eq("setting_key", settingKey).one();
    }

    @Override
    public JSONObject getCache(String key) {
      final String cache = stringRedisTemplate.opsForValue().get(key);
      final JSONObject jsonObject = JSONObject.parseObject(cache);
      if(jsonObject == null){
        throw new BusinessException("域名未配置");
      }
      return jsonObject;
    }

    @Override
    public void initConfig(Setting data) {
      if (data.getSettingKey().equals("payment")) {
        final JSONObject jsonObject = JSONObject.parseObject(data.getContent());
        final String mchId = jsonObject.getString("mchId");
        final String apiclientKey = jsonObject.getString("apiclientKey");
        final String privateKey = pathConfig.getUploadPath().concat("file").concat(apiclientKey);
        final String apiclientCert = pathConfig.getUploadPath().concat("file").concat(jsonObject.getString("apiclientCert"));
        final String merchantSerialNumber = jsonObject.getString("merchantSerialNumber");
        final String apiV3key = jsonObject.getString("wechatApiKey");
        if(config == null){
//          config = new RSAAutoCertificateConfig.Builder()
//            .merchantId(mchId)
//            .privateKeyFromPath("/Users/gxwebsoft/Documents/uploads/file/20230622/fb193d3bfff0467b83dc498435a4f433.pem")
//            .merchantSerialNumber(merchantSerialNumber)
//            .apiV3Key(apiV3key)
//            .build();
          // 使用配置参数而非硬编码
          config =
            new RSAConfig.Builder()
              .merchantId(mchId)
              .privateKeyFromPath(privateKey)
              .merchantSerialNumber(merchantSerialNumber)
              .wechatPayCertificatesFromPath(apiclientCert)
              .build();
          configMap.put(data.getTenantId().toString(),config);
          System.out.println("config = " + config);
        }
        if (service == null) {
          service = new JsapiService.Builder().config(config).build();
        }
      }
    }

    @Override
    public Config getConfig(Integer tenantId) {
      if(configMap.get(tenantId.toString()) == null){
        final Setting payment = getOne(new LambdaQueryWrapper<Setting>().eq(Setting::getSettingKey, "payment"));
        this.initConfig(payment);
        return configMap.get(tenantId.toString());
      }
      return configMap.get(tenantId.toString());
    }

}
