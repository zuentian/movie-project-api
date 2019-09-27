package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.MovieRelName;
import com.zuer.zuerlvdoubancommon.entity.MovieType;
import com.zuer.zuerlvdoubanservice.service.MovieRelNameService;
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
@RequestMapping(value = "/MovieRelName")
@Transactional(rollbackFor = Exception.class)
public class MovieRelNameServiceClient {

    @Autowired
    private MovieRelNameService movieRelNameService;

    @RequestMapping(value = "/insertMovieRelName",method = RequestMethod.POST)
    public void insertMovieRelName(@RequestBody MovieRelName movieRelName){
        movieRelNameService.insertSelective(movieRelName);
    }


    @RequestMapping(value = "/queryMovieTypeByMovieId",method = RequestMethod.GET)
    public List<MovieRelName> queryMovieRelNameByMovieId(@RequestParam("movieId")String movieId){
        Example example=new Example(MovieRelName.class);
        example.createCriteria().andEqualTo("movieId",movieId);
        return movieRelNameService.selectByExample(example);
    }
}
