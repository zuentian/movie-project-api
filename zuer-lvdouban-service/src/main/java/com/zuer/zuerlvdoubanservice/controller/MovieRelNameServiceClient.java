package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.MovieRelName;
import com.zuer.zuerlvdoubanservice.service.MovieRelNameService;
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
@RequestMapping(value = "/MovieRelName")
@Transactional(rollbackFor = Exception.class)
public class MovieRelNameServiceClient {

    @Autowired
    private MovieRelNameService movieRelNameService;

    @RequestMapping(value = "/insertMovieRelName",method = RequestMethod.POST)
    public void insertMovieRelName(@RequestBody MovieRelName movieRelName){
        movieRelNameService.insertSelective(movieRelName);
    }
}
