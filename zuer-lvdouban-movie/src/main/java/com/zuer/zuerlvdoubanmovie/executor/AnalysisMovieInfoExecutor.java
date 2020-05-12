package com.zuer.zuerlvdoubanmovie.executor;

import com.zuer.zuerlvdoubancommon.entity.CrawlerMovieSyncInfo;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerMovieSyncInfoFeignService;
import com.zuer.zuerlvdoubanmovie.thread.common.ThreadPoolPriorityExecutor;
import com.zuer.zuerlvdoubanmovie.thread.entity.ThreadType;
import com.zuer.zuerlvdoubanmovie.thread.service.ExecutorBuilderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
@Component
public class AnalysisMovieInfoExecutor {
    private static final Logger logger= LoggerFactory.getLogger(AnalysisMovieInfoExecutor.class);

    private ThreadPoolPriorityExecutor threadPoolPriorityExecutor;

    @Autowired
    @Qualifier("ExecutorBuilderService")
    private ExecutorBuilderService executorBuilderService;

    @Autowired
    private CrawlerMovieSyncInfoFeignService crawlerMovieSyncInfoFeignService;

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

    public void analysis(CrawlerMovieSyncInfo crawlerMovieSyncInfo) {
        threadPoolPriorityExecutor.execute(new Runnable() {
            @Override
            public void run() {
                logger.info("AnalysisMovieInfoExecutor 开始执行解析任务 id=["+crawlerMovieSyncInfo.getId()+"] start");
                //此处开始整合MQ,将任务发送到MQ方
                try {
                    Thread.sleep(5000);//滞留5s
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("AnalysisMovieInfoExecutor 开始执行解析任务 id=["+crawlerMovieSyncInfo.getId()+"] end");
            }
        });
    }

    public ThreadPoolPriorityExecutor getThreadPoolPriorityExecutor() {
        return threadPoolPriorityExecutor;
    }
}
