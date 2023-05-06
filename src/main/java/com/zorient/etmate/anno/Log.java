package com.zorient.etmate.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */

@Retention(RetentionPolicy.RUNTIME)//该注解何时生效
@Target(ElementType.METHOD)//指定注解在方法上生效
public @interface Log {
}
