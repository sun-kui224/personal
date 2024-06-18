package com.person.common.exception.system;

import com.person.common.exception.BaseException;

/**
 * @author sk
 * @date 2024/5/11
 */
public class UserPasswordRetryLimitExceedException extends BaseException {
    public UserPasswordRetryLimitExceedException(String errorMessage, Object[] args) {
        super("system", errorMessage, args);
    }
}
