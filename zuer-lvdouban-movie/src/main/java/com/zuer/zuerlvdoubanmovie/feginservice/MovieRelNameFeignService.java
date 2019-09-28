package com.zuer.zuerlvdoubanmovie.feginservice;

import com.zuer.zuerlvdoubancommon.entity.MovieRelName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "zuer-lvdouban-service")
public interface MovieRelNameFeignService {


    @RequestMapping(value = "/MovieRelName/insertMovieRelName",method = RequestMethod.POST)
    void insertMovieRelName(@RequestBody MovieRelName movieRelName);
    @RequestMapping(value = "/MovieRelName/queryMovieTypeByMovieId",method = RequestMethod.GET)
    List<MovieRelName> queryMovieRelNameByMovieId(@RequestParam("movieId")String movieId);
    @RequestMapping(value = "/MovieRelName/deleteMovieRelNameByMovieId",method = RequestMethod.GET)
    int deleteMovieRelNameByMovieId(@RequestParam("movieId")String movieId);
}
