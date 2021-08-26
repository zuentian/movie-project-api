package com.zuer.zuerlvdoubanmovie.dao;

import com.zuer.zuerlvdoubancommon.entity.MovieUserCountData;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

    /**
     * @author Zuer
     * @version 1.0
     * @date 2021/8/26 11:10
     */
    public interface MovieUserCountDataDao extends Mapper<MovieUserCountData> {
    /**
     * 新增观影的人数
     * @param movieId
     */
    void insertCountByMovieId(@Param("movieId") String movieId);
    /**
     * 更新观影的人数
     * @param movieId
     */
    void updateCountByMovieId(@Param("movieId") String movieId);

    }
