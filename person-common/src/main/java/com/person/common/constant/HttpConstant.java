package com.person.common.constant;

/**
 * 请求常量
 * @author sk
 * @date 2024/5/11
 */
public class HttpConstant {

    /**
     * 请求成功
     */
    public static final int SUCCESS = 200;

    /**
     * 请求失败
     */
    public static final int ERROR = 500;

    /**
     * 警告
     */
    public static final int WARN = 601;

    /**
     * 无权限
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌
     */
    public static final String TOKEN_PREFIX = "Bearer ";
}
