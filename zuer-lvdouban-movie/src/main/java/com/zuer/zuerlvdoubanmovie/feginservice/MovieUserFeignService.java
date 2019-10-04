package com.zuer.zuerlvdoubanmovie.feginservice;

import com.zuer.zuerlvdoubancommon.entity.MovieUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "zuer-lvdouban-service")
public interface MovieUserFeignService {


    @RequestMapping(value = "/MovieUser/insertMovieUser",method = RequestMethod.POST)
    int insertMovieUser(@RequestBody MovieUser movieUser);

    @RequestMapping(value = "/MovieUser/queryMovieUserByMovieIdFromState2",method = RequestMethod.GET)
    List<MovieUser> queryMovieUserByMovieIdFromState2(@RequestParam("movieId")String movieId);


    @RequestMapping(value = "/MovieUser/queryMovieUserByMovieIdAndUserId",method = RequestMethod.GET)
    List<MovieUser> queryMovieUserByMovieIdAndUserId(@RequestParam("movieId")String movieId, @RequestParam("userId")String userId);

}
