package com.person.common.advice;

import com.person.common.base.domain.AjaxResult;
import com.person.common.constant.HttpConstant;
import com.person.common.exception.ServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 * @author sk
 * @date 2024/5/14
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public AjaxResult handleServiceException(ServiceException ex) {
        AjaxResult error = AjaxResult.error(ex.getCode(),ex.getMessage());
        return error;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder errorMessageBuilder = new StringBuilder("数据验证失败：");

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessageBuilder.append(fieldError.getField())
                    .append(" - ")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        }

        String errorMessage = errorMessageBuilder.toString();
        // 去掉末尾的分号和空格
        errorMessage = errorMessage.substring(0, errorMessage.length() - 2);

        return AjaxResult.error(HttpConstant.ERROR, errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public AjaxResult handleExceptionHandler(Exception ex) {
        AjaxResult error = AjaxResult.error(ex.getMessage());
        return error;
    }
}
