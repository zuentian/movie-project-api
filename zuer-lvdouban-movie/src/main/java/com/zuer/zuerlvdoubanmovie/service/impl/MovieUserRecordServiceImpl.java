package com.zuer.zuerlvdoubanmovie.service.impl;

import com.zuer.zuerlvdoubancommon.entity.MovieUserRecord;
import com.zuer.zuerlvdoubanmovie.dao.MovieUserRecHisDao;
import com.zuer.zuerlvdoubanmovie.dao.MovieUserRecordDao;
import com.zuer.zuerlvdoubanmovie.executor.AnalysisMovieUserCount;
import com.zuer.zuerlvdoubanmovie.service.MovieUserRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/18 11:28
 */
@Service
public class MovieUserRecordServiceImpl implements MovieUserRecordService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MovieUserRecordDao movieUserRecordDao;
    @Resource
    private MovieUserRecHisDao movieUserRecHisDao;
    @Resource
    private AnalysisMovieUserCount analysisMovieUserCount;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertMovieUserRecord(MovieUserRecord movieUserRecord) {
        //先把当前信息落入历史表，当天的不用落入记录历史表
        logger.info("insertByMovieUserRecordAndDay start");
        movieUserRecHisDao.insertByMovieUserRecordAndDay(
                movieUserRecord.getMovieId(),
                movieUserRecord.getUserId(),
                1);

        //删除用户的电影当前信息，并将现在的信息落入表中
        logger.info("insertByMovieUserRecordAndDay end");

        logger.info("deleteByExample start");
        Example ex = new Example(MovieUserRecord.class);
        ex.createCriteria().andEqualTo("userId",movieUserRecord.getUserId());
        movieUserRecordDao.deleteByExample(ex);
        logger.info("deleteByExample end");
        logger.info("insertSelective start");
        movieUserRecordDao.insertSelective(movieUserRecord);
        logger.info("insertSelective end");
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        analysisMovieUserCount.change(movieUserRecord.getState());
                    }
                });
            }
        });

    }
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
            2,
            1000,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(10),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    System.out.println("线程"+r.hashCode()+"创建");
                    //线程命名
                    Thread th = new Thread(r,"threadPool"+r.hashCode());
                    return th;
                }
            },
            new ThreadPoolExecutor.AbortPolicy());
}
