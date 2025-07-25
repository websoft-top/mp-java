package com.gxwebsoft.common.core.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.system.entity.User;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * MybatisPlus配置
 *
 * @author WebSoft
 * @since 2018-02-22 11:29:28
 */
@Configuration
public class MybatisPlusConfig {
    @Resource
    private RedisUtil redisUtil;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(HttpServletRequest request) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 多租户插件配置
        TenantLineHandler tenantLineHandler = new TenantLineHandler() {
            @Override
            public Expression getTenantId() {
              String tenantId;
              // 从请求头拿ID
              tenantId = request.getHeader("tenantId");
              if(tenantId != null){
                return new LongValue(tenantId);
              }
              // 从域名拿ID
              String Domain = request.getHeader("Domain");
              if (StrUtil.isNotBlank(Domain)) {
                String key = "Domain:" + Domain;
                tenantId = redisUtil.get(key);
                if(tenantId != null){
                  System.out.println("从域名拿TID = " + tenantId);
                  return new LongValue(tenantId);
                }
              }
              return getLoginUserTenantId();
            }

            @Override
            public boolean ignoreTable(String tableName) {
                return Arrays.asList(
                        "sys_tenant",
                        "sys_dictionary",
                        "sys_dictionary_data",
                        "apps_test_data",
                        "cms_lang"
//                        "hjm_car",
//                        "hjm_fence"
//                        "cms_website"
//                        "sys_user"
//                        "cms_domain"
//                        "shop_order_goods",
//                        "shop_goods"
//                        "shop_users",
//                        "shop_order",
//                        "shop_order_info",
//                        "booking_user_invoice"
                ).contains(tableName);
            }
        };
        TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor(tenantLineHandler);
        interceptor.addInnerInterceptor(tenantLineInnerInterceptor);

        // 分页插件配置
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        paginationInnerInterceptor.setMaxLimit(2000L);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        return interceptor;
    }

    /**
     * 获取当前登录用户的租户id
     *
     * @return Integer
     */
    public Expression getLoginUserTenantId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                Object object = authentication.getPrincipal();
                if (object instanceof User) {
                    return new LongValue(((User) object).getTenantId());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new NullValue();
    }

}
