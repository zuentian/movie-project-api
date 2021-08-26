package com.zuer.zuerlvdoubanmovie.service;

import com.zuer.zuerlvdoubancommon.entity.MovieUserCountData;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/26 10:52
 */
public interface MovieUserCountDataService {
    /**
     * 搜索条件 movieId type
     * @param movieId
     * @param type
     * @return
     */
    MovieUserCountData queryMovieUserCountDataByMovieId(String movieId, String type);

    /**
     * 计算电影关于用户的数量 新增
     * @param movieId
     * @param type
     */
    void insertForMovieUser(String movieId, String type);
    /**
     * 计算电影关于用户的数量 更新
     * @param movieId
     * @param type
     */
    void updateForMovieUser(String movieId, String type);
}
