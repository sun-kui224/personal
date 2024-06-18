package com.person.common.base.domain;

import com.person.common.domain.PMenu;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author sk
 * @date 2024/5/11
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账号
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 名称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    private List<PMenu> pMenus;
}
