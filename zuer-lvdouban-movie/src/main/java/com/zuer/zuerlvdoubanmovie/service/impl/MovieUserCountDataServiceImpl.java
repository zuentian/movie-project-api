package com.zuer.zuerlvdoubanmovie.service.impl;

import com.zuer.zuerlvdoubancommon.entity.MovieUserCountData;
import com.zuer.zuerlvdoubanmovie.dao.MovieUserCountDataDao;
import com.zuer.zuerlvdoubanmovie.service.MovieUserCountDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/26 10:52
 */
@Service
public class MovieUserCountDataServiceImpl implements MovieUserCountDataService {
    @Resource
    private MovieUserCountDataDao movieUserCountDataDao;

    @Override
    public void updateCountByMovieId(String movieId) {
        movieUserCountDataDao.updateCountByMovieId(movieId);
    }

    @Override
    public MovieUserCountData queryMovieUserCountDataByMovieId(String movieId) {
        return movieUserCountDataDao.selectByPrimaryKey(movieId);
    }

    @Override
    public void insertCountByMovieId(String movieId) {
        movieUserCountDataDao.insertCountByMovieId(movieId);
    }
}
