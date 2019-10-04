package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.MovieUser;
import com.zuer.zuerlvdoubanservice.service.MovieUserService;
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
@RequestMapping(value = "/MovieUser")
@Transactional(rollbackFor = Exception.class)
public class MovieUserServiceClient {


    @Autowired
    public MovieUserService movieUserService;
    @RequestMapping(value = "/insertMovieUser",method = RequestMethod.POST)
    public int insertMovieUser(@RequestBody MovieUser movieUser){
        return movieUserService.insertSelective(movieUser);
    }



    @RequestMapping(value = "/queryMovieUserByMovieIdFromState2",method = RequestMethod.GET)
    public List<MovieUser> queryMovieUserByMovieIdFromState2(@RequestParam("movieId")String movieId){
        Example example=new Example(MovieUser.class);
        example.createCriteria().andEqualTo("movieId",movieId);
        example.createCriteria().andEqualTo("state","2");
        return movieUserService.selectByExample(example);
    }


    @RequestMapping(value = "/queryMovieUserByMovieIdAndUserId",method = RequestMethod.GET)
    public List<MovieUser> queryMovieUserByMovieIdAndUserId(@RequestParam("movieId")String movieId, @RequestParam("userId")String userId){
        Example example=new Example(MovieUser.class);
        example.createCriteria().andEqualTo("movieId",movieId);
        example.createCriteria().andEqualTo("userId",userId);
        return movieUserService.selectByExample(example);
    }
}
