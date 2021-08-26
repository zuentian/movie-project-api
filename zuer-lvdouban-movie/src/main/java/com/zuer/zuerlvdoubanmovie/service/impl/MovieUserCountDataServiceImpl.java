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
    public MovieUserCountData queryMovieUserCountDataByMovieId(String movieId, String type) {
        MovieUserCountData data = new MovieUserCountData();
        data.setMovieId(movieId);
        data.setType(type);
        return movieUserCountDataDao.selectByPrimaryKey(data);
    }

    @Override
    public void insertForMovieUser(String movieId, String type) {
        movieUserCountDataDao.insertForMovieUser(movieId,type);
    }

    @Override
    public void updateForMovieUser(String movieId, String type) {
        movieUserCountDataDao.updateForMovieUser(movieId,type);
    }
}
