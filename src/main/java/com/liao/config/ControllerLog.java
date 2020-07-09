package com.liao.config;

import java.lang.annotation.*;

/**
 * Controller 层日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {
    String description()default "";

}
