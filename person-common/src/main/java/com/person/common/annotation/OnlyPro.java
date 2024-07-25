package com.person.common.annotation;

import java.lang.annotation.*;

/**
 * @author sk
 * @date 2024/7/19
 * @description 自定义注解防止唯一字段重复
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnlyPro {

    /**
     * 属性值，逗号隔开
     * @return
     */
    String value();

    /**
     * 间隔时间(ms)，小于此时间打回请求,默认1秒
     */
    public int interval() default 1000;

    /**
     * 提示消息
     */
    public String message() default "{name}{{value}}已存在，请检查后重新提交";

    /**
     * 字段名称,逗号隔开
     * @return
     */
    public String name() default "字段";
}
