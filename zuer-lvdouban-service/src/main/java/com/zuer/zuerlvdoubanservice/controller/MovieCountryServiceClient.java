package com.zuer.zuerlvdoubanservice.controller;


import com.zuer.zuerlvdoubancommon.entity.MovieCountry;
import com.zuer.zuerlvdoubanservice.service.MovieCountryService;
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
@RequestMapping(value = "/MovieCountry")
@Transactional(rollbackFor = Exception.class)
public class MovieCountryServiceClient {

    @Autowired
    private MovieCountryService movieCountryService;

    @RequestMapping(value = "/insertMovieCountry",method = RequestMethod.POST)
    public void insertMovieCountry(@RequestBody  MovieCountry movieCountry) {
        movieCountryService.insertSelective(movieCountry);
    }
}
