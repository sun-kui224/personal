package com.person.system.aspect;

import com.alibaba.fastjson2.JSON;
import com.person.common.annotation.Log;
import com.person.common.base.domain.LoginUser;
import com.person.common.domain.PLog;
import com.person.common.enums.HttpMethod;
import com.person.common.enums.OperStatus;
import com.person.common.filter.PropertyPreExcludeFilter;
import com.person.common.utils.*;
import com.person.system.config.execute.AsyncManager;
import com.person.system.service.IPLogService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * aop插入日志
 * @author sk
 * @date 2024/5/17
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /** 排除敏感属性字段 */
    public static final String[] EXCLUDE_SENSITIVE = { "password"};

    /** 计算时间 */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("time consuming");

    /**
     * 处理请求前执行
     */
    @Before(value = "@annotation(log)")
    public void boBefore(JoinPoint joinPoint, Log log)
    {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(log)", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Log log, Object result)
    {
        handleLog(joinPoint, log, null, result);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "@annotation(log)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log log, Exception e)
    {
        handleLog(joinPoint, log, e, null);
    }

    /**
     * 记录日志
     * @param joinPoint
     * @param log        日志注解对象
     * @param e          异常对象
     * @param result     返回结果对象
     */
    protected void handleLog(final JoinPoint joinPoint, Log log, final Exception e, Object result) {
        try
        {
            // 获取当前用户
            LoginUser loginUser = SecurityUtils.getLoginUser();
            PLog pLog = new PLog();
            pLog.setId(IdUtils.fastId());
            pLog.setOperName(loginUser.getUsername());
            pLog.setOperIp(IpUtils.getIpAddr());
            if (e != null) {
                pLog.setOperStatus(OperStatus.FAIL.getCode());
                pLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }else{
                pLog.setOperStatus(OperStatus.SUCCESS.getCode());
            }
            pLog.setOperTime(DateUtils.getNowDate());

            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            pLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            pLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            pLog.setReqUrl(StringUtils.substring(ServletUtils.getRequest().getRequestURI(), 0, 255));
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, log, pLog, result);
            // 设置消耗时间
            pLog.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());
            // 保存数据库
            AsyncManager.ma().execute(()->ApplicationUtils.getBean(IPLogService.class).insertPLog(pLog));
        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            logger.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
        finally
        {
            TIME_THREADLOCAL.remove();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log 日志
     * @param pLog 操作日志对象
     * @throws Exception
     */
    protected void getControllerMethodDescription(JoinPoint joinPoint, Log log, PLog pLog, Object result) throws Exception
    {
        // 设置action动作
        pLog.setOperType(log.businessType().getType());
        // 设置标题
        pLog.setTitle(log.title());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData())
        {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, pLog, log.excludeParamNames());
        }
        // 是否需要保存response，参数和值
        if (log.isSaveResponseData() && ObjectUtils.isNotEmpty(result))
        {
            pLog.setResResult(StringUtils.substring(JSON.toJSONString(result), 0, 2000));
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param pLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, PLog pLog, String[] excludeParamNames) throws Exception
    {
        Map<?, ?> paramsMap = ServletUtils.getParamMap(ServletUtils.getRequest());
        String requestMethod = pLog.getRequestMethod();
        if (ObjectUtils.isEmpty(paramsMap) && (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)))
        {
            String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
            pLog.setReqParam(StringUtils.substring(params, 0, 2000));
        }
        else
        {
            pLog.setReqParam(StringUtils.substring(JSON.toJSONString(paramsMap, excludePropertyPreFilter(excludeParamNames)), 0, 2000));
        }
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames)
    {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0)
        {
            for (Object o : paramsArray)
            {
                if (ObjectUtils.isNotEmpty(o))
                {
                    try
                    {
                        String jsonObj = JSON.toJSONString(o, excludePropertyPreFilter(excludeParamNames));
                        params += jsonObj.toString() + " ";
                    }
                    catch (Exception e)
                    {
                    }
                }
            }
        }
        return params.trim();
    }

    /**
     * 忽略敏感属性
     */
    public PropertyPreExcludeFilter excludePropertyPreFilter(String[] excludeParamNames)
    {
        return new PropertyPreExcludeFilter().addExcludes(ArrayUtils.addAll(EXCLUDE_SENSITIVE, excludeParamNames));
    }
}
