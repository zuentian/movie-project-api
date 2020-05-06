package com.zuer.zuerlvdoubanmovie.thread.service;

import com.zuer.zuerlvdoubanmovie.thread.common.ThreadPoolPriorityExecutor;

public interface ExecutorBuilderService {
    ThreadPoolPriorityExecutor createPriorityExecutorInstance(String type);
}
