package com.zuer.zuerlvdoubanmovie.executor;

import com.zuer.zuerlvdoubanmovie.thread.common.ThreadPoolPriorityExecutor;
import com.zuer.zuerlvdoubanmovie.thread.entity.ThreadType;
import com.zuer.zuerlvdoubanmovie.thread.service.ExecutorBuilderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class AnalysisMovieInfoExecutor {
    private static final Logger logger= LoggerFactory.getLogger(AnalysisMovieInfoExecutor.class);

    private ThreadPoolPriorityExecutor threadPoolPriorityExecutor;

    @Autowired
    @Qualifier("ExecutorBuilderService")
    private ExecutorBuilderService executorBuilderService;
    //修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次
    @PostConstruct
    public void init(){
        try {
            String type = ThreadType.ANALY_DB_MOVIE_INFO.getValue();
            logger.info("AnalysisMovieInfoExecutor 初始化线程池 type=["+type+"]");
            threadPoolPriorityExecutor = executorBuilderService.createPriorityExecutorInstance(type) ;
        }catch (Exception e){
            logger.error("初始化线程池出错");
        }
    }
    //修饰的方法会在服务器卸载Servlet的时候运行，并且只会被服务器调用一次
    @PreDestroy
    public void destory(){

    }
}
