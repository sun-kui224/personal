package com.person.system.config.execute;

import com.person.common.utils.ThreadsUtils;

/**
 * 当线程中抛出未捕获的异常时，就会走这里
 * @author sk
 * @date 2024/5/18
 */
public class AsyncUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        ThreadsUtils.printException(t, e);
    }
}
