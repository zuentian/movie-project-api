package com.zuer.zuerlvdoubanservice.service;

import com.zuer.zuerlvdoubancommon.entity.MoviePictureInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MoviePictureInfoService extends Mapper<MoviePictureInfo> {
    List<MoviePictureInfo> queryMoviePictureInfoByMovieIdFromSix(@Param("movieId") String movieId);
}
