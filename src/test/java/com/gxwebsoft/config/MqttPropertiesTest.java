package com.gxwebsoft.config;

import com.gxwebsoft.common.core.config.MqttProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

/**
 * MQTT配置属性测试类
 *
 * @author 科技小王子
 * @since 2025-07-02
 */
@SpringBootTest
@ActiveProfiles("dev")
public class MqttPropertiesTest {
    
    @Resource
    private MqttProperties mqttProperties;
    
    @Test
    public void testMqttPropertiesLoading() {
        System.out.println("=== MQTT配置属性测试 ===");
        System.out.println("Host: " + mqttProperties.getHost());
        System.out.println("Username: " + mqttProperties.getUsername());
        System.out.println("Password: " + (mqttProperties.getPassword() != null ? "***" : "null"));
        System.out.println("ClientIdPrefix: " + mqttProperties.getClientIdPrefix());
        System.out.println("Topic: " + mqttProperties.getTopic());
        System.out.println("QoS: " + mqttProperties.getQos());
        System.out.println("ConnectionTimeout: " + mqttProperties.getConnectionTimeout());
        System.out.println("KeepAliveInterval: " + mqttProperties.getKeepAliveInterval());
        System.out.println("AutoReconnect: " + mqttProperties.isAutoReconnect());
        System.out.println("CleanSession: " + mqttProperties.isCleanSession());
        
        // 验证关键配置不为空
        assert mqttProperties.getHost() != null : "Host不能为空";
        assert mqttProperties.getClientIdPrefix() != null : "ClientIdPrefix不能为空";
        assert mqttProperties.getTopic() != null : "Topic不能为空";
        
        System.out.println("MQTT配置属性测试通过！");
    }
}
