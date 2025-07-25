package com.gxwebsoft;

import com.gxwebsoft.common.core.config.ConfigProperties;
import com.gxwebsoft.common.core.config.MqttProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * 启动类
 * Created by WebSoft on 2018-02-22 11:29:03
 */
@EnableAsync
@EnableTransactionManagement
@MapperScan("com.gxwebsoft.**.mapper")
@EnableConfigurationProperties({ConfigProperties.class, MqttProperties.class})
@SpringBootApplication
@EnableScheduling
@EnableWebSocket
public class WebSoftApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSoftApplication.class, args);
    }

}
