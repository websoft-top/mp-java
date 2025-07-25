package com.gxwebsoft.common.core.annotation;

import java.lang.annotation.*;

/**
 * 操作日志模块注解
 *
 * @author WebSoft
 * @since 2021-09-01 20:48:16
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationModule {

    /**
     * 模块名称
     */
    String value();

}
