package com.zuer.zuerlvdoubanmovie.executor.impl;

import com.zuer.zuerlvdoubancommon.constants.RedisKeys;
import com.zuer.zuerlvdoubanmovie.executor.AnalysisMovieData;
import com.zuer.zuerlvdoubanmovie.service.MovieUserCountDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/24 16:55
 */
@Service
public class AnalysisMovieDataImpl implements AnalysisMovieData {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MovieUserCountDataService movieUserCountDataService;

    //@Resource
    //private RedisTemplate<String,Object> template;

    /**
     * 计算用户表里的数据
     * @param movieId
     * @param userId
     * @param state
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changeUserId(String movieId,String userId,String state) {
        logger.info("AnalysisMovieUserCountImpl change() 计算该电影的想看和看过的数量 start " +
                "movieId=[{}]",movieId);
        String key = RedisKeys.USER_WATCH_STATE.getCode().concat(movieId);
        //this.loadCountByMovieId(movieId);
        //if(!template.hasKey(key)){
            
        //}
        logger.info("AnalysisMovieUserCountImpl change() 计算该电影的想看和看过的数量 end " +
                "movieId=[{}]",movieId);
    }

    /*public void loadCountByMovieId(String movieId){
        String key = RedisKeys.USER_WATCH_COUNT_VO.getCode().concat(movieId);
        MovieUserCountData movieUserCountData = null;
        if(!template.hasKey(key)){
            movieUserCountData = movieUserCountDataService.queryMovieUserCountDataByMovieId(movieId);
            Map<String,Integer> movieUserCountDataMap = new HashMap<String, Integer>(16) ;
            movieUserCountDataMap.put("beforeWatchCt",movieUserCountData.getBeforeWatchCt());
            movieUserCountDataMap.put("afterWatchCt",movieUserCountData.getAfterWatchCt());
            template.opsForHash().putAll(key,movieUserCountDataMap);
        }
    }*/
}
