package com.zuer.zuerlvdoubanmovie.service.impl;

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
    public void replaceIntoMovieUserCountData(String movieId) {
        movieUserCountDataDao.replaceIntoMovieUserCountData(movieId);
    }
}
