package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.MovieType;
import com.zuer.zuerlvdoubanservice.service.MovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
