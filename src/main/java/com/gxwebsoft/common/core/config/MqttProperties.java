package com.gxwebsoft.common.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * MQTT配置属性
 *
 * @author 科技小王子
 * @since 2025-07-02
 */
@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {
    
    /**
     * MQTT服务器地址
     */
    private String host = "tcp://127.0.0.1:1883";
    
    /**
     * 用户名
     */
    private String username = "";
    
    /**
     * 密码
     */
    private String password = "";
    
    /**
     * 客户端ID前缀
     */
    private String clientIdPrefix = "mqtt_client_";
    
    /**
     * 订阅主题
     */
    private String topic = "/SW_GPS/#";
    
    /**
     * QoS等级
     */
    private int qos = 2;
    
    /**
     * 连接超时时间（秒）
     */
    private int connectionTimeout = 10;
    
    /**
     * 心跳间隔（秒）
     */
    private int keepAliveInterval = 20;
    
    /**
     * 是否自动重连
     */
    private boolean autoReconnect = true;
    
    /**
     * 是否清除会话
     */
    private boolean cleanSession = false;
}
