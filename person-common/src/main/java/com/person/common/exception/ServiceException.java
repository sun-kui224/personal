package com.person.common.exception;

/**
 * 系统服务异常
 * @author sk
 * @date 2024/5/11
 */
public class ServiceException extends RuntimeException{


    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    public ServiceException() {
    }
    public ServiceException(String message,Integer code) {
        this.message = message;
        this.code = code;
    }
    public ServiceException(String message) {
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
