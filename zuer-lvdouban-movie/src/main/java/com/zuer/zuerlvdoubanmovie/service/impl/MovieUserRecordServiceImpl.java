package com.zuer.zuerlvdoubanmovie.service.impl;

import com.zuer.zuerlvdoubancommon.entity.MovieUserRecord;
import com.zuer.zuerlvdoubanmovie.dao.MovieUserRecHisDao;
import com.zuer.zuerlvdoubanmovie.dao.MovieUserRecordDao;
import com.zuer.zuerlvdoubanmovie.service.MovieUserRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertMovieUserRecord(MovieUserRecord movieUserRecord) {

        logger.info("insertByMovieUserRecordAndDay start");
        Example ex = new Example(MovieUserRecord.class);
        ex.createCriteria().andEqualTo("userId",movieUserRecord.getUserId());
        ex.createCriteria().andEqualTo("movieId",movieUserRecord.getMovieId());
        int count = movieUserRecordDao.selectCountByExample(ex);
        if(count>0){
            /**
             * 特别注明，
             * mysql 中 insert into A select * from B 会导致B表全表加锁，
             * 就算是在后面加上 where 条件并且用到了一些特殊字段，如果B表里没有一条满足的数据，仍然不会只锁固定行数，从而导致全表加锁
             * 如果这个时候B表有其他的插入语句进来，会导致死锁
             */
            movieUserRecHisDao.insertByMovieUserRecordAndDay(
                    movieUserRecord.getMovieId(),
                    movieUserRecord.getUserId(),
                    1);
            movieUserRecordDao.updateByPrimaryKeySelective(movieUserRecord);
        }else{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            movieUserRecordDao.replaceIntoMovieUserRecord(movieUserRecord);
        }
        logger.info("insertByMovieUserRecordAndDay end");
    }

}
