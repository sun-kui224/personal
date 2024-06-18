package com.person.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 线程工具类
 * @author sk
 * @date 2024/5/18
 */
public class ThreadsUtils {

    private static final Logger logger = LoggerFactory.getLogger(ThreadsUtils.class);

    /**
     * 停止线程池
     * 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
     * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数.
     * 如果仍然超時，則強制退出.
     * 另对在shutdown时线程本身被调用中断做了处理.
     *
     * @param pool            要关闭的线程池
     * @param shutdownTimeout 初次关闭尝试的超时时间
     * @param forceTimeout    若初次关闭失败，强制关闭的超时时间
     */
    public static void shutdownAndAwaitTermination(ExecutorService pool, long shutdownTimeout, long forceTimeout) {
        if (pool != null && !pool.isShutdown()) {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(shutdownTimeout, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    if (!pool.awaitTermination(forceTimeout, TimeUnit.SECONDS)) {
                        logger.info("线程池未在指定时间内停止，名称: {}", pool.toString());
                    }
                }
            } catch (InterruptedException | SecurityException ie) {
                logger.error("线程池关闭过程中发生异常", ie);
                pool.shutdownNow();
                // 重新设置中断状态
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 打印线程异常信息
     */
    public static void printException(Runnable r, Throwable t)
    {
        if (t == null && r instanceof Future<?>)
        {
            try
            {
                Future<?> future = (Future<?>) r;
                if (future.isDone())
                {
                    future.get();
                }
            }
            catch (CancellationException ce)
            {
                t = ce;
            }
            catch (ExecutionException ee)
            {
                t = ee.getCause();
            }
            catch (InterruptedException ie)
            {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null)
        {
            logger.error(t.getMessage(), t);
        }
    }
}
