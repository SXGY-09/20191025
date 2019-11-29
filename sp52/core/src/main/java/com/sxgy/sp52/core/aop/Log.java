package com.sxgy.sp52.core.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author SXGY_09
 * @description 操作日志注解
 * @date 2019-11-29 18:41
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.PARAMETER})
public @interface Log {
    String name() default "模块";

    String value() default "内容";
}
