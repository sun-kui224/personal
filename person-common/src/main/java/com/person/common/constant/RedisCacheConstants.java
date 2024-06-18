package com.person.common.constant;

/**
 * redis缓存key常量
 * @author sk
 * @date 2024/5/11
 */
public class RedisCacheConstants {

    /**
     * 登录用户token
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 密码错误次数
     */
    public static final String PWD_ERR_COUNTS = "pwd_err_counts:";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";
}
