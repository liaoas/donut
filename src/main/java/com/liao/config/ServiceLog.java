package com.liao.config;

import java.lang.annotation.*;

/**
 * Service 层日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {
    String description()default "";
}
