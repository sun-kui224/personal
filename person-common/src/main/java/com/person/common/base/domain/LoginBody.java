package com.person.common.base.domain;

import lombok.Data;

/**
 * 登录接收实体对象
 * @author sk
 * @date 2024/5/11
 */
@Data
public class LoginBody {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

}
