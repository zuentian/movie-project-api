package com.zuer.zuerlvdoubanmovie.thread.service.impl;

import com.zuer.zuerlvdoubanmovie.thread.common.ThreadPoolPriorityExecutor;
import com.zuer.zuerlvdoubanmovie.thread.service.ExecutorBuilderService;
import org.springframework.stereotype.Service;

@Service("ExecutorBuilderService")
public class ExecutorBuilderServiceImpl implements ExecutorBuilderService {
    @Override
    public ThreadPoolPriorityExecutor createPriorityExecutorInstance(String type) {
        return null;
    }
}
