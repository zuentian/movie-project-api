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
     * 计算电影的用户观影量
     * @param movieId
     */
    void replaceIntoMovieUserCountData(@Param("movieId") String movieId);
}
