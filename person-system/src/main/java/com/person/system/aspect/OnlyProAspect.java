package com.person.system.aspect;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.common.annotation.OnlyPro;
import com.person.common.constant.HttpConstant;
import com.person.common.exception.ServiceException;
import com.person.common.utils.ServletUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

/**
 * @author sk
 * @date 2024/7/19
 * @description 结合redis做唯一字段的提前校验，防止插入相同值
 * 注意：key是：类名+字段名+字段值。
 */
@Component
@Aspect
public class OnlyProAspect {

    protected final Logger logger = LoggerFactory.getLogger(OnlyProAspect.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String DEFAULT = "";

    @Around("@annotation(onlyPro)")
    public Object around(ProceedingJoinPoint point, OnlyPro onlyPro) throws Throwable {
        HttpServletRequest request = ServletUtils.getRequest();  //当前请求
        if(!checkRequest(request)) return point.proceed();

        Map<String, Object> paramsMap = parseJsonToMap(point);
        String[] values = onlyPro.value().split(",");  //字段值
        int interval = onlyPro.interval();                   //时间
        String message = onlyPro.message();                  //提示信息
        String[] names = onlyPro.name().split(",");    //字段名

        // 获取被拦截的方法所在类名
        Class<?> targetClass = point.getTarget().getClass();
        String controllerName = targetClass.getName();

        List<String> keys = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            Object parameter = paramsMap.get(value);
            String key = controllerName + "-" + value + "-" + parameter;

            // 尝试获取锁
            if (!redisTemplate.opsForValue().setIfAbsent(key, DEFAULT, Duration.ofMillis(interval))) {
                // 如果未获取到锁，抛出异常或返回错误信息
                String error = errorMessage(i, names, message, parameter);
                throw new ServiceException(error, HttpConstant.WARN);
            } else {
                // 如果获取到锁，添加到待清理的keys列表
                keys.add(key);
            }
        }
        try{
            return point.proceed();
        }finally{
            //清除key
           redisTemplate.delete(keys);
        }
    }

    /**
     * 获取数据
     * @return
     * @throws IOException
     */
    private Map<String, Object> parseJsonToMap(ProceedingJoinPoint point) {
        Map<String,Object>allMap = new HashMap<>();
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if(arg instanceof MultipartFile){
                continue;
            }else if(arg instanceof Map) {
                allMap.putAll((Map<String, Object>)arg);
            }else {
                Map<String, Object> map = objectToMap(arg);
                allMap.putAll(map);
            }
        }
        return allMap;
    }

    /**
     * 对象转map
     * @param obj
     * @return
     */
    private Map<String, Object> objectToMap(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            Map<String, Object> map = objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
            return map;
        }catch (Exception e){
            logger.error(e.getMessage());
            return new HashMap<>();
        }
    }

    /**
     * 拼接错误信息
     * @param index   下标
     * @param names    字段名
     * @param message  提示信息
     * @param parameter 字段值
     * @return
     */
    public String errorMessage(int index,String[] names,String message,Object parameter){
        if (message == null) {
            message = "{name}{{value}}已存在，请检查后重新提交";
        }
        String name = index < names.length ? names[index] : "字段";
        return message.replace("{name}", name).replace("{value}", String.valueOf(parameter));
    }

    /**
     * 校验
     * @param request
     * @return
     */
    public Boolean checkRequest(HttpServletRequest request){
        String requestMethod = request.getMethod();
        if ("POST".equals(requestMethod) || "PUT".equals(requestMethod)) {
            return true;
        }
        return false;
    }

}
