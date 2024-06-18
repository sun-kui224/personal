package com.person.system.service;

import com.person.common.base.domain.LoginUser;
import com.person.common.constant.UserConstant;
import com.person.common.domain.PPerson;
import com.person.common.exception.ServiceException;
import com.person.common.exception.system.UserCheckException;
import com.person.common.utils.IdUtils;
import com.person.system.security.context.AuthenticationContextHolder;
import com.person.common.utils.SecurityUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sk
 * @date 2024/5/11
 */
@Service
public class LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IPPersonService ppersonService;

    public String login(String username,String password){
        // 登录前置校验
        loginPreCheck(username,password);
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        //记录登录日志
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }

    public void register(String username,String password){
        // 登录前置校验
        loginPreCheck(username,password);
        PPerson pPerson = ppersonService.selectPPersonByLoginName(username);
        if (ObjectUtils.isEmpty(pPerson) || UserConstant.DELETED == pPerson.getDelFlag()) {
            PPerson person = new PPerson();
            person.setLoginName(username);
            person.setPassword(SecurityUtils.encryptPassword(password));
            person.setId(IdUtils.fastId());
            person.setDelFlag(0);
            person.setStatus(0);
            ppersonService.insertPPerson(person);
        }
    }

    public void loginPreCheck(String username,String password){
        if(ObjectUtils.isEmpty(username)){
            //记录日志
            throw new UserCheckException("账号不能为空");
        }
        // 用户名或密码为空 错误
        if (ObjectUtils.isEmpty(password))
        {
            throw new UserCheckException("密码不能为空");
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstant.USER_NAME_MIX_LENGTH
                || username.length() > UserConstant.USER_NAME_MAX_LENGTH)
        {
            throw new UserCheckException("账号长度不合规");
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstant.PASSWORD_MIX_LENGTH
                || password.length() > UserConstant.PASSWORD_MAX_LENGTH)
        {
            throw new UserCheckException("密码长度不合规");
        }
    }
}
