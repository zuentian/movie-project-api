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
     * 计算电影关于用户的数量 新增
     * @param movieId
     * @param type
     */
    void insertForMovieUser(@Param("movieId") String movieId, @Param("type") String type);

    void updateForMovieUser(@Param("movieId") String movieId, @Param("type") String type);
}
