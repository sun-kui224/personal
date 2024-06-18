package com.person.common.annotation;

import com.person.common.enums.OperateType;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author sk
 * @date 2024/5/17
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 标题
     */
    public String title() default "";

    /**
     * 操作类型
     */
    public OperateType businessType() default OperateType.OTHER;

    /**
     * 默认保存请求参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 默认保存响应参数
     */
    public boolean isSaveResponseData() default true;

    /**
     * 排除指定请求参数
     */
    public String[] excludeParamNames() default {};
}
