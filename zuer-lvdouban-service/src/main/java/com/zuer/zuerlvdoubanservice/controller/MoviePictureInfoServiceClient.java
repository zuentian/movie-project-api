package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.MoviePictureInfo;
import com.zuer.zuerlvdoubanservice.service.MoviePictureInfoService;
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
@RequestMapping(value = "/MoviePictureInfo")
@Transactional(rollbackFor = Exception.class)
public class MoviePictureInfoServiceClient {

    @Autowired
    private MoviePictureInfoService moviePictureInfoService;

    @RequestMapping(value = "/insertMovieInfo",method = RequestMethod.POST)
    public void insertMoviePictureInfo(@RequestBody MoviePictureInfo moviePictureInfo){
        moviePictureInfoService.insertSelective(moviePictureInfo);
    }

    @RequestMapping(value = "/queryMoviePictureInfoByMovieIdFromSix",method = RequestMethod.GET)
    public List<MoviePictureInfo> queryMoviePictureInfoByMovieIdFromSix(@RequestParam("movieId") String movieId){
        return moviePictureInfoService.queryMoviePictureInfoByMovieIdFromSix(movieId);
    }
    @RequestMapping(value = "/queryMoviePictureInfoByMovieIdCount",method = RequestMethod.GET)
    public int queryMoviePictureInfoByMovieIdCount(@RequestParam("movieId") String movieId){
        Example example=new Example(MoviePictureInfo.class);
        example.createCriteria().andEqualTo("movieId",movieId);
        return moviePictureInfoService.selectCountByExample(example);
    }
}
