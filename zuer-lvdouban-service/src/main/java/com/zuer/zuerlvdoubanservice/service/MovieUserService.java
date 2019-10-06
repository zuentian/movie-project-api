package com.zuer.zuerlvdoubanservice.service;

import com.zuer.zuerlvdoubancommon.entity.MovieUser;
import com.zuer.zuerlvdoubancommon.vo.MovieUserCommand;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MovieUserService extends Mapper<MovieUser> {

    int queryMovieUserScoreCountByMovieIdAndScore(@Param("movieId") String movieId,@Param("score")  String score);

    List<MovieUserCommand> queryShortCommandByMovieId(@Param("movieId") String movieId);
}
