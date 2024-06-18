package com.person.system.aspect;

import com.person.common.annotation.Page;
import com.person.common.base.domain.AjaxResult;
import com.person.common.base.domain.PageParam;
import com.person.common.base.domain.PageResult;
import com.person.common.exception.ServiceException;
import com.person.common.utils.ConverUtil;
import com.person.common.utils.PageUtils;
import com.person.common.utils.ServletUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * aop分页
 * @author sk
 * @date 2024/5/14
 */
@Component
@Aspect
public class PageAspect {

    @Around("@annotation(page)")
    public Object around(ProceedingJoinPoint point, Page page) throws Throwable {
        PageParam pageParam = null;
        //判断接口上有PageParam参数就手动，否则自动分页
        boolean isPage = true;
        Object[] args = point.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof PageParam) {
                pageParam = (PageParam) args[i];
                isPage = false;
                break;
            }
        }
        if(pageParam==null){
            HttpServletRequest request = ServletUtils.getRequest();
            int pageNum = ConverUtil.toInt(request.getParameter("pageNum"),1);
            int pageSize = ConverUtil.toInt(request.getParameter("pageSize"),10);
            pageParam = new PageParam(pageNum, pageSize);
            PageUtils.pageStart(pageParam);
        }
        Object proceed = point.proceed(point.getArgs());
        if(isPage && (proceed instanceof AjaxResult)){
            AjaxResult result = (AjaxResult)proceed;
            PageResult data = PageUtils.pageEnd((List) result.get("data"));
            result.put("data",data);
            return result;
        }else{
            return proceed;
        }
    }
}
