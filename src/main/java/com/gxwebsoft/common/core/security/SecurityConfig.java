package com.gxwebsoft.common.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * Spring Security配置
 *
 * @author WebSoft
 * @since 2020-03-23 18:04:52
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/file/**","/**", "/api/captcha", "/")
                .permitAll()
                .antMatchers(
                        "/api/login",
                        "/api/register",
                        "/api/cms/website/createWebsite",
                        "/druid/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/swagger-ui/**",
                        "/api/open/**",
                        "/hxz/v1/**",
                        "/api/sendSmsCaptcha",
                        "/api/login-alipay/*",
                        "/api/wx-login/loginByMpWxPhone",
                        "/api/shop/payment/mp-alipay/notify",
                        "/api/shop/payment/mp-alipay/test/**",
                        "/api/shop/payment/mp-alipay/getPhoneNumber",
                        "/api/cms/cms-order/**",
                        "/api/shop/shop-order/notify/**",
                        "/api/mp/mp/component_verify_ticket",
                        "/api/mp/mp/callback",
                        "/api/shop/test/**",
                        "/api/shop/wx-login/**",
                        "/api/shop/wx-native-pay/**",
                        "/api/shop/wx-pay/**",
                        "/api/bszx/bszx-pay/notify/**",
                        "/api/wxWorkQrConnect",
                        "/WW_verify_QMv7HoblYU6z63bb.txt",
                        "/5zbYEPkyV4.txt",
                        "/api/love/user-plan-log/wx-pay/**",
                        "/api/cms/form-record",
                        "/api/shop/merchant-account/getMerchantAccountByPhone",
                        "/api/hjm/hjm-car/**",
                        "/api/chat/**"
                  )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .cors()
                .and()
                .logout()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
