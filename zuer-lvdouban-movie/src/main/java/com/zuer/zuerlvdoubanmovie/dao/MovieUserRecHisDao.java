package com.zuer.zuerlvdoubanmovie.dao;

import com.zuer.zuerlvdoubancommon.entity.MovieUserRecHis;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/20 10:28
 */
public interface MovieUserRecHisDao extends Mapper<MovieUserRecHis> {
    /**
     * 将Movie_User_Record的数据落到历史表
     * @param movieId
     * @param userId
     * @param day
     */
    void insertByMovieUserRecordAndDay(@Param("movieId") String movieId,
                                       @Param("userId")String userId,
                                       @Param("day")int day);
}
