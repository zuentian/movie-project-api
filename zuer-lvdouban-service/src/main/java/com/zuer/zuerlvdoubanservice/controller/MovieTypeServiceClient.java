package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.MovieType;
import com.zuer.zuerlvdoubanservice.service.MovieTypeService;
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
@RequestMapping(value = "/MovieType")
@Transactional(rollbackFor = Exception.class)
public class MovieTypeServiceClient {

    @Autowired
    private MovieTypeService movieTypeService;
    @RequestMapping(value = "/insertMovieType",method = RequestMethod.POST)
    public void insertMovieType(@RequestBody MovieType movieType){
        movieTypeService.insertSelective(movieType);
    }


    @RequestMapping(value = "/queryMovieTypeByMovieId",method = RequestMethod.GET)
    public List<MovieType> queryMovieTypeByMovieId(@RequestParam("movieId")String movieId){
        Example example=new Example(MovieType.class);
        example.createCriteria().andEqualTo("movieId",movieId);
        return movieTypeService.selectByExample(example);
    }

    @RequestMapping(value = "/deleteMovieTypeByMovieId",method = RequestMethod.GET)
    public int deleteMovieTypeByMovieId(@RequestParam("movieId")String movieId){
        Example example=new Example(MovieType.class);
        example.createCriteria().andEqualTo("movieId",movieId);
        return movieTypeService.deleteByExample(example);
    }
}
