package com.zuer.zuerlvdoubanmovie.executor.impl;

import com.zuer.zuerlvdoubanmovie.executor.AnalysisUserMovieTypeCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/27 10:57
 */
@Service
public class AnalysisUserMovieTypeCountImpl implements AnalysisUserMovieTypeCount {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addMovieTypeCount(String userId, String movieType) {
        logger.info("AnalysisMovieUserCountImpl addMovieTypeCount() 计算用户看过的电影类型数量 start " +
                "userId=[{}] movieType=[{}]",userId,movieType);
        //考虑计算更新会比较慢，此处阻塞15秒
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("AnalysisMovieUserCountImpl addMovieTypeCount() 计算用户看过的电影类型数量 end " +
                "userId=[{}] movieType=[{}]",userId,movieType);
    }
}
