package com.zuer.zuerlvdoubanmovie.executor.impl;

import com.zuer.zuerlvdoubancommon.constants.RedisKeys;
import com.zuer.zuerlvdoubanmovie.config.RedisLock;
import com.zuer.zuerlvdoubanmovie.executor.AnalysisMovieData;
import com.zuer.zuerlvdoubanmovie.service.MovieUserCountDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.UUID;

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

    @Resource
    private JedisPool jedisPool;


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
        String key = RedisKeys.MOVIE_USER_WATCH_COUNT.getCode().concat(movieId);
        RedisLock tool = new RedisLock(jedisPool);
        String id = UUID.randomUUID().toString();
        try {
            tool.lock(id,key);
            movieUserCountDataService.replaceIntoMovieUserCountData(movieId);
        } finally {
            tool.unlock(id,key);
        }
        logger.info("AnalysisMovieUserCountImpl change() 计算该电影的想看和看过的数量 end " +
                "movieId=[{}]",movieId);
    }
}
