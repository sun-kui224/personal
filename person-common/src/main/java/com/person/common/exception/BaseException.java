package com.person.common.exception;

import org.apache.commons.lang3.ObjectUtils;

import java.text.MessageFormat;

/**
 * @author sk
 * @date 2024/5/11
 */
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误消息
     */
    private String errorMessage;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    public static final String defaultMessage = "系统内部错误，请联系管理员";

    public BaseException(String module, String errorMessage, Object[] args)
    {
        this.module = module;
        this.errorMessage = errorMessage;
        this.args = args;
    }

    public BaseException(String module, String errorMessage)
    {
        this(module, errorMessage, null);
    }

    public BaseException(String errorMessage, Object[] args)
    {
        this(null, errorMessage, args);
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!ObjectUtils.isEmpty(errorMessage)) {
            try {
                message = MessageFormat.format(errorMessage, args);
            } catch (IllegalArgumentException e) {
                // 当args为空或者格式错误时，直接返回原始的errorMessage
                message = errorMessage;
            }
        }
        if (message == null)
        {
            message = defaultMessage;
        }
        return message;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
