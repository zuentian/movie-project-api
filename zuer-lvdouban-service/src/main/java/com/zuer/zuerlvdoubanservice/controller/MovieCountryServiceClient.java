package com.zuer.zuerlvdoubanservice.controller;


import com.zuer.zuerlvdoubancommon.entity.MovieCountry;
import com.zuer.zuerlvdoubanservice.service.MovieCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/MovieCountry")
@Transactional(rollbackFor = Exception.class)
public class MovieCountryServiceClient {

    @Autowired
    private MovieCountryService movieCountryService;

    @RequestMapping(value = "/insertMovieCountry",method = RequestMethod.POST)
    public void insertMovieCountry(@RequestBody  MovieCountry movieCountry) {
        movieCountryService.insertSelective(movieCountry);
    }

    @RequestMapping(value = "/queryMovieCountryByMovieId",method = RequestMethod.GET)
    public List<MovieCountry> queryMovieCountryByMovieId(@RequestParam("movieId") String movieId){
        Example example=new Example(MovieCountry.class);
        example.createCriteria().andEqualTo("movieId",movieId);
        return movieCountryService.selectByExample(example);

    }
    @RequestMapping(value = "/deleteMovieCountryByMovieId",method = RequestMethod.GET)
    public int deleteMovieCountryByMovieId(@RequestParam("movieId") String movieId){
        Example example=new Example(MovieCountry.class);
        example.createCriteria().andEqualTo("movieId",movieId);
        return movieCountryService.deleteByExample(example);
    }
}
