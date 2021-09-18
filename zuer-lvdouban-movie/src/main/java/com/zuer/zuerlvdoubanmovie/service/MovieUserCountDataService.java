package com.zuer.zuerlvdoubanmovie.service;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/26 10:52
 */
public interface MovieUserCountDataService {

    /**
     * 计算电影的用户观影量
     * @param movieId
     */
    void replaceIntoMovieUserCountData(String movieId);
}
