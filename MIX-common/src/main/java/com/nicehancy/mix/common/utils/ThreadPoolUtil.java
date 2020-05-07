package com.nicehancy.mix.common.utils;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.*;

/**
 * 线程池工具类
 * <p>
 * <p/>
 *
 * @author hancaiyun
 * @since 2020/4/26 15:30
 **/
@Slf4j
public class ThreadPoolUtil {

    /**
     * 设置核心池大小
     */
    private static final int CORE_POOL_SIZE = 8;

    /**
     * 设置线程池最大能接受多少线程
     */
    private static final int MAX_POOL_SIZE = 16;

    /**
     * 当前线程数大于corePoolSize、小于maximumPoolSize时，超出corePoolSize的线程数的生命周期
     */
    private static final long KEEP_ALIVE_TIME = 500;

    /**
     * 设置时间单位，秒
     */
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 队列默认长度为10
     */
    private static final LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>(10);

    /**
     * 线程池创建线程使用的工厂
     */
    private static final ThreadFactory threadFactory = Executors.defaultThreadFactory();

    /**
     * 创建ThreadPoolExecutor线程池对象，并初始化该对象的各种参数
     */
    private static final ExecutorService uploadExportPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
            KEEP_ALIVE_TIME, TIME_UNIT, linkedBlockingQueue, threadFactory);

    /**
     * 执行方法
     * @param runnable  线程
     */
    public static void execute(Runnable runnable) {
        log.info("当前线程队列任务数量：{}", linkedBlockingQueue.size());
        try {
            uploadExportPool.execute(runnable);
        } catch (RejectedExecutionException rejectedExecutionException) {
            log.error("当前线程池已饱和，ThreadId：{}, size：{}", Thread.currentThread().getId(), linkedBlockingQueue.size());
        }
    }
}
