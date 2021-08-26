package com.zuer.zuerlvdoubanmovie.service;

import com.zuer.zuerlvdoubancommon.entity.MovieUserCountData;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/26 10:52
 */
public interface MovieUserCountDataService {

    /**
     * 更新观影的人数
     * @param movieId
     */
    void updateCountByMovieId(String movieId);

    /**
     * 查询观影人数 条件movieId
     * @param movieId
     * @return
     */
    MovieUserCountData queryMovieUserCountDataByMovieId(String movieId);
    /**
     * 新增观影的人数
     * @param movieId
     */
    void insertCountByMovieId(String movieId);
}
