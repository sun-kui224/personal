package com.person.system.service;

import com.person.common.constant.RedisCacheConstants;
import com.person.common.domain.PPerson;
import com.person.common.exception.system.UserCheckException;
import com.person.common.exception.system.UserPasswordRetryLimitExceedException;
import com.person.system.security.context.AuthenticationContextHolder;
import com.person.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 登录密码方法
 *
 * @author sk
 */
@Component
public class SysPasswordService {
    @Autowired
    private RedisCache redisCache;

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount;

    @Value(value = "${user.password.lockTime}")
    private int lockTime;

    /**
     * 登录账户密码错误次数缓存键名
     *
     * @param username 用户名
     * @return 缓存键key
     */
    private String getCacheKey(String username) {
        return RedisCacheConstants.PWD_ERR_COUNTS + username;
    }

    public void validate(PPerson pPerson)
    {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String username = usernamePasswordAuthenticationToken.getName();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();

        Integer retryCount = redisCache.getCacheObject(getCacheKey(username));

        if (retryCount == null)
        {
            retryCount = 0;
        }

        if (retryCount >= Integer.valueOf(maxRetryCount).intValue())
        {
            throw new UserPasswordRetryLimitExceedException("密码错误达到了{0},密码锁定{1}分钟",new Object[]{maxRetryCount,lockTime});
        }

        if (!matches(pPerson, password))
        {
            retryCount = retryCount + 1;
            redisCache.setCacheObject(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);
            throw new UserCheckException("密码错误，请重试");
        }
        else
        {
            clearLoginRecordCache(username);
        }
    }

    public boolean matches(PPerson pPerson, String rawPassword)
    {
        return SecurityUtils.matchesPassword(rawPassword, pPerson.getPassword());
    }

    public void clearLoginRecordCache(String loginName)
    {
        if (redisCache.hasKey(getCacheKey(loginName)))
        {
            redisCache.deleteObject(getCacheKey(loginName));
        }
    }
}
