package com.wedcloud.springboot.annotation;

import java.lang.annotation.*;

/**
 * @author xuhb
 * <p>测试注解</p>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Token {
    String value();
    String name() default "";
}
