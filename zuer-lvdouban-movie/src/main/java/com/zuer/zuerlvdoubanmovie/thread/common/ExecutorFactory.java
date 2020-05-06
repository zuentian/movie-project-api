package com.zuer.zuerlvdoubanmovie.thread.common;

import com.zuer.zuerlvdoubanmovie.thread.entity.ThreadProperties;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ExecutorFactory {


    /*
       int corePoolSize:线程池维护线程的最小数量.
    　　int maxiPoolSize:线程池维护线程的最大数量.
    　　long keepAliveTime:空闲线程的存活时间.
    　　TimeUnit unit: 时间单位,现有纳秒,微秒,毫秒,秒枚举值.
    　　BlockingQueue<Runnable> workQueue:持有等待执行的任务队列.
    　　RejectedExecutionHandler handler:拒绝策略
     */
    public static ThreadPoolTaskExecutor create(ThreadProperties threadProperties){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(threadProperties.getThreadNamePrefix());
        executor.setCorePoolSize(threadProperties.getCorePoolSize());
        executor.setMaxPoolSize(threadProperties.getMaxiPoolSize());
        executor.setKeepAliveSeconds(threadProperties.getKeepAliveSeconds());
        //拒绝策略的一种，如果添加到线程池失败，那么主线程会自己去执行该任务，不会等待线程池中的线程去执行。
        executor.setRejectedExecutionHandler(threadProperties.getRejectedExecutionHandler());
        executor.initialize();
        return executor;
    }

}
