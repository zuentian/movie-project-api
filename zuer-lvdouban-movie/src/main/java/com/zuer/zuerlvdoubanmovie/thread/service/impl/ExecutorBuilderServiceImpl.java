package com.zuer.zuerlvdoubanmovie.thread.service.impl;

import com.zuer.zuerlvdoubancommon.entity.ThreadPropertiesInfo;
import com.zuer.zuerlvdoubanmovie.feginservice.ThreadPropertiesInfoFeignService;
import com.zuer.zuerlvdoubanmovie.thread.common.ExecutorFactory;
import com.zuer.zuerlvdoubanmovie.thread.common.ThreadPoolPriorityExecutor;
import com.zuer.zuerlvdoubanmovie.thread.entity.ThreadProperties;
import com.zuer.zuerlvdoubanmovie.thread.service.ExecutorBuilderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zuer
 */
@Service("ExecutorBuilderService")
public class ExecutorBuilderServiceImpl implements ExecutorBuilderService {

    @Resource
    private ThreadPropertiesInfoFeignService threadPropertiesInfoFeignService;

    @Override
    public ThreadPoolPriorityExecutor createPriorityExecutorInstance(String type) {
        ThreadPropertiesInfo threadPropertiesInfo = null;
        try {
            threadPropertiesInfo = threadPropertiesInfoFeignService.queryThreadPropertiesInfoByType(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExecutorFactory.create(setThreadProperties(threadPropertiesInfo));
    }

    private ThreadProperties setThreadProperties(ThreadPropertiesInfo threadPropertiesInfo) {
        ThreadProperties threadProperties = new ThreadProperties();
        threadProperties.setCorePoolSize(threadPropertiesInfo == null?10:threadPropertiesInfo.getCorePoolSize());
        threadProperties.setMaxiPoolSize(threadPropertiesInfo == null?10:threadPropertiesInfo.getMaxPoolSize());
        threadProperties.setQueueCapacity(threadPropertiesInfo == null?60:threadPropertiesInfo.getQueueCapacity());
        return threadProperties;
    }

    @Override
    public synchronized void updateExecutor(ThreadPoolPriorityExecutor executor, String type) {
        ThreadPropertiesInfo threadPropertiesInfo = threadPropertiesInfoFeignService.queryThreadPropertiesInfoByType(type);
        ExecutorFactory.update(executor,setThreadProperties(threadPropertiesInfo));
    }
}
