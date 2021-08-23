package com.zuer.zuerlvdoubanmovie.service.impl;

import com.zuer.zuerlvdoubancommon.entity.MovieUserRecord;
import com.zuer.zuerlvdoubanmovie.dao.MovieUserRecHisDao;
import com.zuer.zuerlvdoubanmovie.dao.MovieUserRecordDao;
import com.zuer.zuerlvdoubanmovie.service.MovieUserRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

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
        //先把当前信息落入历史表，当天的不用落入记录历史表
        logger.info("insertByMovieUserRecordAndDay start");
        movieUserRecHisDao.insertByMovieUserRecordAndDay(
                movieUserRecord.getMovieId(),
                movieUserRecord.getUserId(),
                1);

        //删除用户的电影当前信息，并将现在的信息落入表中
        logger.info("insertByMovieUserRecordAndDay end");
        int i = 1/0;
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
                logger.info("afterCommit()");
            }
        });
    }
}
