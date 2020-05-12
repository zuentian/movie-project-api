package com.zuer.zuerlvdoubanmovie.thread.service.impl;

import com.zuer.zuerlvdoubancommon.entity.ThreadPropertiesInfo;
import com.zuer.zuerlvdoubanmovie.feginservice.ThreadPropertiesInfoFeignService;
import com.zuer.zuerlvdoubanmovie.thread.common.ExecutorFactory;
import com.zuer.zuerlvdoubanmovie.thread.common.ThreadPoolPriorityExecutor;
import com.zuer.zuerlvdoubanmovie.thread.entity.ThreadProperties;
import com.zuer.zuerlvdoubanmovie.thread.service.ExecutorBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ExecutorBuilderService")
public class ExecutorBuilderServiceImpl implements ExecutorBuilderService {

    @Autowired
    private ThreadPropertiesInfoFeignService threadPropertiesInfoFeignService;

    @Override
    public ThreadPoolPriorityExecutor createPriorityExecutorInstance(String type) {
        ThreadPropertiesInfo threadPropertiesInfo = threadPropertiesInfoFeignService.queryThreadPropertiesInfoByType(type);
        ThreadProperties threadProperties = new ThreadProperties();
        threadProperties.setCorePoolSize(threadPropertiesInfo == null?10:threadPropertiesInfo.getCorePoolSize());
        threadProperties.setMaxiPoolSize(threadPropertiesInfo == null?10:threadPropertiesInfo.getMaxPoolSize());
        threadProperties.setQueueCapacity(threadPropertiesInfo == null?60:threadPropertiesInfo.getQueueCapacity());
        return ExecutorFactory.create(threadProperties);
    }

    @Override
    public synchronized void updateExecutor(ThreadPoolPriorityExecutor executor, String type) {
        ThreadPropertiesInfo threadPropertiesInfo = threadPropertiesInfoFeignService.queryThreadPropertiesInfoByType(type);
        ThreadProperties threadProperties = new ThreadProperties();
        threadProperties.setCorePoolSize(threadPropertiesInfo == null?10:threadPropertiesInfo.getCorePoolSize());
        threadProperties.setMaxiPoolSize(threadPropertiesInfo == null?10:threadPropertiesInfo.getMaxPoolSize());
        threadProperties.setQueueCapacity(threadPropertiesInfo == null?60:threadPropertiesInfo.getQueueCapacity());
        ExecutorFactory.update(executor,threadProperties);
    }
}
