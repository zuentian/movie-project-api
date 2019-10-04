package com.zuer.zuerlvdoubanservice.service;

import com.zuer.zuerlvdoubancommon.entity.MovieInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MovieInfoService extends Mapper<MovieInfo> {

    List<MovieInfo> queryMovieInfoByParam(Map<String, Object> map);

    int queryMovieInfoByParamCount(Map<String, Object> map);

    MovieInfo queryMovieInfoById(String id);

    void addWatchBeforeNumber(String id);

    void addWatchAfterNumber(String id);

    void updateMovieInfoByIdFromScore(@Param("id") String id, @Param("score")String score, @Param("personScoreCount") Integer personScoreCount);
}
