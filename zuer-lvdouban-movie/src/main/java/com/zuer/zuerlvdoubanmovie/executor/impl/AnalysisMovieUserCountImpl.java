package com.zuer.zuerlvdoubanmovie.executor.impl;

import com.zuer.zuerlvdoubancommon.entity.MovieUserCountData;
import com.zuer.zuerlvdoubanmovie.executor.AnalysisMovieUserCount;
import com.zuer.zuerlvdoubanmovie.service.MovieUserCountDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/24 16:55
 */
@Service
public class AnalysisMovieUserCountImpl implements AnalysisMovieUserCount {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final long UPD_DATE_MAX = 15 * 60 * 1000;
    @Resource
    private MovieUserCountDataService movieUserCountDataService;

    /**
     * 计算用户表里的数据，根据电影计量表的更新时间，如果更新时间超过十五分钟才要重新计算
     * @param movieId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void change(String movieId) {
        logger.info("AnalysisMovieUserCountImpl change() 计算该电影的想看和看过的数量 start " +
                "movieId=[{}]",movieId);
        MovieUserCountData data = movieUserCountDataService.queryMovieUserCountDataByMovieId(movieId);
        if(data == null){
            movieUserCountDataService.insertCountByMovieId(movieId);
        } else {
            if(UPD_DATE_MAX >= data.getUpdDate().getTime()){
                movieUserCountDataService.updateCountByMovieId(movieId);
            }
        }
        //考虑计算更新会比较慢，此处阻塞15秒
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("AnalysisMovieUserCountImpl change() 计算该电影的想看和看过的数量 end " +
                "movieId=[{}]",movieId);
    }
}
