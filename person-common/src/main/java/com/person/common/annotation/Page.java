package com.person.common.annotation;

import java.lang.annotation.*;

/**
 * 分页注解
 * @author sk
 * @date 2024/5/14
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Page {
}
