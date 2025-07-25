package com.gxwebsoft.common.core.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.RequestHandler;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import com.google.common.base.Function;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * Swagger配置
 *
 * @author WebSoft
 * @since 2018-02-22 11:29:05
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {
    @Resource
    private ConfigProperties config;

    private static final String SPLITOR = ";";

    @Bean(value = "common")
    public Docket CoreApi() {
        Docket docket = new Docket(DocumentationType.OAS_30);

        final ApiInfo info = new ApiInfoBuilder()
                .title(config.getSwaggerTitle())
                .description(config.getSwaggerDescription())
                .version(config.getSwaggerVersion())
                .contact(contact())
                .termsOfServiceUrl("https://server.gxwebsoft.com/api/system")
                .build();

        return docket
                .apiInfo(info)
                .groupName("common")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gxwebsoft.common"))
                .paths(PathSelectors.any())
                .build()
                .host("server.gxwebsoft.com")
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    @Bean(value = "cms")
    public Docket CmsApi() {
        Docket docket = new Docket(DocumentationType.OAS_30);
        if (StrUtil.isNotBlank(config.getSwaggerHost())) {
            docket.host(config.getSwaggerHost());
        }
        ApiInfo apiInfoBuilder = new ApiInfoBuilder()
                .title("CMS模块")
                .description(config.getSwaggerDescription())
                .version(config.getSwaggerVersion())
                .termsOfServiceUrl("https://modules.gxwebsoft.com/api/cms")
                .contact(contact())
                .build();

        return docket
                .apiInfo(apiInfoBuilder)
                //分组名称
                .groupName("cms")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.gxwebsoft.cms"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }



    @Bean(value = "shop")
    public Docket ShopApi() {
        Docket docket = new Docket(DocumentationType.OAS_30);
        if (StrUtil.isNotBlank(config.getSwaggerHost())) {
            docket.host(config.getSwaggerHost());
        }
        ApiInfo apiInfoBuilder = new ApiInfoBuilder()
                .title("Shop模块")
                .description(config.getSwaggerDescription())
                .version(config.getSwaggerVersion())
                .termsOfServiceUrl("https://modules.gxwebsoft.com/api/shop")
                .contact(contact())
                .build();

        return docket
                .apiInfo(apiInfoBuilder)
                //分组名称
                .groupName("shop")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.gxwebsoft.shop"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }


    @Bean(value = "oa")
    public Docket OaApi() {
        Docket docket = new Docket(DocumentationType.OAS_30);
        if (StrUtil.isNotBlank(config.getSwaggerHost())) {
            docket.host(config.getSwaggerHost());
        }
        ApiInfo apiInfoBuilder = new ApiInfoBuilder()
                .title("OA模块")
                .description(config.getSwaggerDescription())
                .version(config.getSwaggerVersion())
                .termsOfServiceUrl("https://modules.gxwebsoft.com/api/oa")
                .contact(contact())
                .build();

        return docket
                .apiInfo(apiInfoBuilder)
                //分组名称
                .groupName("oa")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.gxwebsoft.oa"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private Contact contact() {
        return new Contact("科技小王子", "https://www.gxwebsoft.com", "170083662@qq.com");
    }

    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(
                new ApiKey("Authorization", "Authorization", "header")
        );
    }

    private List<SecurityContext> securityContexts() {
        AuthorizationScope[] scopes = {new AuthorizationScope("global", "accessEverything")};
        List<SecurityReference> references = Collections.singletonList(
                new SecurityReference("Authorization", scopes)
        );
        return Collections.singletonList(SecurityContext.builder()
                .securityReferences(references)
                .build());
    }

    /**
     * @description 重写basePackage方法，使能够实现多包访问
     * @param basePackage 所有包路径
     * @return Predicate<RequestHandler>
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).map(handlerPackage(basePackage)::apply).orElse(true);
    }

    /**
     * @description 重写basePackage方法，使能够实现多包访问
     * @param basePackage 所有包路径
     * @return Function<Class<?>, Boolean>
     */
    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(SPLITOR)) {
                assert input != null;
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    /**
     * @description 重写basePackage方法，使能够实现多包访问
     * @param input
     * @return Optional<? extends Class<?>>
     */
    private static Optional<Class<?>> declaringClass(RequestHandler input) {
        return Optional.ofNullable(input.declaringClass());
    }

}
