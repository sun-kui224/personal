package com.person.system.config.execute;

import com.person.common.utils.ApplicationUtils;
import com.person.common.utils.ThreadsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理对象
 * @author sk
 * @date 2024/5/18
 */
public class AsyncManager {

    private static final Logger logger = LoggerFactory.getLogger(AsyncManager.class);

    /**
     * 操作延迟时间（毫秒）
     */
    private final int OPERATE_DELAY_TIME;

    /**
     * 异步操作任务调度线程池
     */
    private ScheduledExecutorService executor;

    //单列
    private static AsyncManager ma = new AsyncManager();

    private AsyncManager(){
        this.OPERATE_DELAY_TIME = 10;
        this.executor = ApplicationUtils.getBean("scheduledExecutorService");
    }

    public static AsyncManager ma() {
        return ma;
    }

    /**
     * 执行任务
     *
     * @param task 任务
     */
    public void execute(Runnable task) {
        executor.schedule(() -> {
            try {
                task.run();
            } catch (Exception e) {
                logger.error("任务执行失败", e);
            }
        }, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        ThreadsUtils.shutdownAndAwaitTermination(executor,120,120);
    }

    @Override
    protected void finalize() throws Throwable {
        shutdown();
        super.finalize();
    }
}
