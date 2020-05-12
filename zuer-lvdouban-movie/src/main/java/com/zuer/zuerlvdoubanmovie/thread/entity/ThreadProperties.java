package com.zuer.zuerlvdoubanmovie.thread.entity;

import lombok.Data;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Data
public class ThreadProperties {

    private String threadNamePrefix = "ZUER_EXECUTOR";

    private int corePoolSize;//核心线程数

    private int maxiPoolSize;//最大线程数

    private int queueCapacity;//队列最大长度

    private int keepAliveSeconds = 60 ;

    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
}
