package com.person.common.exception.system;

/**
 * 用户校验异常
 * @author sk
 * @date 2024/5/11
 */
public class UserCheckException extends RuntimeException{

    public UserCheckException(String message) {
        super(message);
    }
}
