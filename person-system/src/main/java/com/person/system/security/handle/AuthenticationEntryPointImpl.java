package com.person.system.security.handle;

import com.alibaba.fastjson2.JSON;
import com.person.common.base.domain.AjaxResult;
import com.person.common.constant.HttpConstant;
import com.person.common.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;

/**
 * 认证失败处理类 返回未授权
 *
 * @author sk
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException
    {
        int code = HttpConstant.UNAUTHORIZED;
        String message = MessageFormat.format("请求访问：{0}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, message)));
    }
}
