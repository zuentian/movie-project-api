package com.zuer.zuerlvdoubanmovie.service.impl;

import com.zuer.zuerlvdoubancommon.entity.MovieUserRecord;
import com.zuer.zuerlvdoubanmovie.dao.MovieUserRecHisDao;
import com.zuer.zuerlvdoubanmovie.dao.MovieUserRecordDao;
import com.zuer.zuerlvdoubanmovie.service.MovieUserRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/18 11:28
 */
@Service
public class MovieUserRecordServiceImpl implements MovieUserRecordService {
    @Resource
    private MovieUserRecordDao movieUserRecordDao;
    @Resource
    private MovieUserRecHisDao movieUserRecHisDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertMovieUserRecord(MovieUserRecord movieUserRecord) {
        //先把当前信息落入历史表
        movieUserRecHisDao.insertByMovieUserRecord(movieUserRecord.getMovieId(),movieUserRecord.getUserId());
        //删除用户当前信息
        Example ex = new Example(MovieUserRecord.class);
        ex.createCriteria().andEqualTo("userId",movieUserRecord.getUserId());
        movieUserRecordDao.deleteByExample(ex);
        movieUserRecordDao.insertSelective(movieUserRecord);
        //movieUserRecordDao
    }
}
