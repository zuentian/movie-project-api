package com.zuer.zuerlvdoubanmovie.feginservice;

import com.zuer.zuerlvdoubancommon.entity.MovieCountry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "zuer-lvdouban-service")
public interface MovieCountryFeignService {

    @RequestMapping(value = "/MovieCountry/insertMovieCountry",method = RequestMethod.POST)
    void insertMovieCountry(@RequestBody MovieCountry movieCountry) ;
    @RequestMapping(value = "/MovieCountry/queryMovieCountryByMovieId",method = RequestMethod.GET)
    List<MovieCountry> queryMovieCountryByMovieId(@RequestParam("movieId") String movieId);
}
