package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.MovieInfo;
import com.zuer.zuerlvdoubanservice.service.MovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/MovieInfo")
@Transactional(rollbackFor = Exception.class)
public class MovieInfoServiceClient {

    @Autowired
    private MovieInfoService movieInfoService;

    @RequestMapping(value = "/insertMovieInfo",method = RequestMethod.POST)
    public void insertMovieInfo(@RequestBody MovieInfo movieInfo) throws Exception{
        movieInfoService.insertSelective(movieInfo);
    }


    @RequestMapping(value = "/queryMovieInfoByParam",method = RequestMethod.POST)
    public List<MovieInfo> queryMovieInfoByParam(@RequestBody Map<String, Object> map){
        return movieInfoService.queryMovieInfoByParam(map);
    }


    @RequestMapping(value = "/queryMovieInfoByParamCount",method = RequestMethod.POST)
    public int queryMovieInfoByParamCount(@RequestBody Map<String, Object> map){
        return movieInfoService.queryMovieInfoByParamCount(map);
    }


    @RequestMapping(value = "/queryMovieInfoById",method = RequestMethod.GET)
    public MovieInfo queryMovieInfoById(@RequestParam("id") String id){
        return movieInfoService.queryMovieInfoById(id);
    }


    @RequestMapping(value = "/updateMovieInfoById",method = RequestMethod.POST)
    public void updateMovieInfoById(@RequestBody MovieInfo movieInfo){
        movieInfoService.updateByPrimaryKeySelective(movieInfo);
    }


    @RequestMapping(value = "/deleteMovieInfoById",method = RequestMethod.GET)
    public void deleteMovieInfoById(@RequestParam("id")  String id){
        movieInfoService.deleteByPrimaryKey(id);
    }
}
