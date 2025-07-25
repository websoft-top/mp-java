package com.gxwebsoft.common.core.config;

import com.gxwebsoft.common.core.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置, 拦截器、资源映射等都在此配置
 *
 * @author WebSoft
 * @since 2019-06-12 10:11:16
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 支持跨域访问
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .exposedHeaders(Constants.TOKEN_HEADER_NAME)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
