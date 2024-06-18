package com.person.system.config.execute;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 线程池配置
 * @author sk
 * @date 2024/5/18
 */
@Configuration
public class AsyncConfig {

    // 核心线程池大小
    private int corePoolSize = 50;

    // 线程池维护线程所允许的空闲时间
    private int keepAliveSeconds = 300;


    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService()
    {
        ScheduledThreadPoolExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(corePoolSize);

        scheduledExecutor.setThreadFactory(new BasicThreadFactory.Builder()
                .namingPattern("schedule-pool-%d")
                .daemon(true)
                //添加自定义异常处理
                .uncaughtExceptionHandler(asyncUncaughtExceptionHandler())
                .build());

        // 设置拒绝策略
        scheduledExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 设置核心线程存活时间
        scheduledExecutor.setKeepAliveTime(keepAliveSeconds, TimeUnit.SECONDS);

        return scheduledExecutor;
    }

    @Bean
    protected AsyncUncaughtExceptionHandler asyncUncaughtExceptionHandler()
    {
       return new AsyncUncaughtExceptionHandler();
    }
}
