package com.zuer.zuerlvdoubanmovie.service.impl;

import com.zuer.zuerlvdoubancommon.entity.MovieUserRecord;
import com.zuer.zuerlvdoubanmovie.dao.MovieUserRecordDao;
import com.zuer.zuerlvdoubanmovie.service.MovieUserRecordService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/18 11:28
 */
@Service("MovieUserRecordService")
public class MovieUserRecordServiceImpl implements MovieUserRecordService {
    //@Resource
    //private MovieUserRecordDao movieUserRecordDao;

    @Override
    public void insertMovieUserRecord(MovieUserRecord movieUserRecord) {
        //movieUserRecordDao.insertSelective(movieUserRecord);
    }
}
