package com.zuer.zuerlvdoubanmovie.feginservice;

import com.zuer.zuerlvdoubancommon.entity.MovieType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "zuer-lvdouban-service")
public interface MovieTypeFeignService {


    @RequestMapping(value = "/MovieType/insertMovieType",method = RequestMethod.POST)
    void insertMovieType(@RequestBody MovieType movieType);
    @RequestMapping(value = "/MovieType/queryMovieTypeByMovieId",method = RequestMethod.GET)
    List<MovieType> queryMovieTypeByMovieId(@RequestParam("movieId")String movieId);
    @RequestMapping(value = "/MovieType/deleteMovieTypeByMovieId",method = RequestMethod.GET)
    int deleteMovieTypeByMovieId(@RequestParam("movieId")String movieId);
}
