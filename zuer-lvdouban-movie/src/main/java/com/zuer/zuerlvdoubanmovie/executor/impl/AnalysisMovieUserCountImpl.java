package com.zuer.zuerlvdoubanmovie.executor.impl;

import com.zuer.zuerlvdoubanmovie.executor.AnalysisMovieUserCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/24 16:55
 */
@Service
public class AnalysisMovieUserCountImpl implements AnalysisMovieUserCount {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //看过
    private static final String MOVIE_WATCH_BEFORE = "2";
    //想看
    private static final String MOVIE_WATCH_AFTER = "1";


    @Override
    public void change(String state) {
        logger.info("AnalysisMovieUserCountImpl change() state=[{}]",state);
        //if(MOVIE_WATCH_BEFORE.){}
    }
}
