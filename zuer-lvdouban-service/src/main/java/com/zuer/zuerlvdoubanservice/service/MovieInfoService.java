package com.zuer.zuerlvdoubanservice.service;

import com.zuer.zuerlvdoubancommon.entity.MovieInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MovieInfoService extends Mapper<MovieInfo> {

    List<MovieInfo> queryMovieInfoByParam(Map<String, Object> map);

    int queryMovieInfoByParamCount(Map<String, Object> map);

    MovieInfo queryMovieInfoById(String id);
}
