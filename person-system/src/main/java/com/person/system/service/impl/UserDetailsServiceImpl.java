package com.person.system.service.impl;

import com.person.common.base.domain.LoginUser;
import com.person.common.base.domain.User;
import com.person.common.constant.UserConstant;
import com.person.common.domain.PMenu;
import com.person.common.domain.PPerson;
import com.person.common.exception.ServiceException;
import com.person.system.service.IPMenuService;
import com.person.system.service.IPPersonService;
import com.person.system.service.SysPasswordService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户校验处理
 * @author sk
 * @date 2024/5/11
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IPPersonService ppersonService;

    @Autowired
    private SysPasswordService sysPasswordService;

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PPerson pPerson = ppersonService.selectPPersonByLoginName(username);
        if (ObjectUtils.isEmpty(pPerson)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("账户不存在");
        }
        else if (UserConstant.DELETED == pPerson.getDelFlag() )
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("用户已被删除");
        }
        else if (UserConstant.DISABLE == pPerson.getStatus())
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException("用户已封停");
        }
        sysPasswordService.validate(pPerson);
        return createLoginUser(pPerson);
    }

    public UserDetails createLoginUser(PPerson pPerson)
    {
        User user = new User();
        user.setNickName(pPerson.getNickName());
        user.setLoginName(pPerson.getLoginName());
        user.setAvatar(pPerson.getAvatar());
        user.setUserId(pPerson.getId());
        user.setPassword(pPerson.getPassword());
        return new LoginUser(null,null,null, user);
    }
}
