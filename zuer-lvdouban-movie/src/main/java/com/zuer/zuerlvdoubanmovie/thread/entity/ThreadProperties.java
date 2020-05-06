package com.zuer.zuerlvdoubanmovie.thread.entity;

import lombok.Data;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Data
public class ThreadProperties {

    private String threadNamePrefix = "ZUER_EXECUTOR";

    private int corePoolSize;

    private int maxiPoolSize;

    private int keepAliveSeconds = 60 ;

    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
}
