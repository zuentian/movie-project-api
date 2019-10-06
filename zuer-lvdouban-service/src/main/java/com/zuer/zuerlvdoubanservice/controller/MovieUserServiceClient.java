package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.MovieUser;
import com.zuer.zuerlvdoubancommon.vo.DictValue;
import com.zuer.zuerlvdoubancommon.vo.MovieUserCommand;
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
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("movieId",movieId);
        criteria.andEqualTo("state","2");
        return movieUserService.selectByExample(example);
    }


    @RequestMapping(value = "/queryMovieUserByMovieIdAndUserId",method = RequestMethod.GET)
    public List<MovieUser> queryMovieUserByMovieIdAndUserId(@RequestParam("movieId")String movieId, @RequestParam("userId")String userId){
        Example example=new Example(MovieUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("movieId",movieId);
        criteria.andEqualTo("userId",userId);
        return movieUserService.selectByExample(example);
    }

    @RequestMapping(value = "/queryMovieUserScoreCountByMovieIdAndScore",method = RequestMethod.GET)
    public int queryMovieUserScoreCountByMovieIdAndScore(@RequestParam("movieId") String movieId, @RequestParam("score")String score){
        return movieUserService.queryMovieUserScoreCountByMovieIdAndScore(movieId,score);
    }

    @RequestMapping(value = "/deleteMovieUserByMovieIdAndUserId",method = RequestMethod.GET)
    public int deleteMovieUserByMovieIdAndUserId(@RequestParam("movieId")String movieId, @RequestParam("userId")String userId){
        Example example=new Example(MovieUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("movieId",movieId);
        criteria.andEqualTo("userId",userId);
        return movieUserService.deleteByExample(example);
    }

    @RequestMapping(value = "/queryMovieUserById",method = RequestMethod.GET)
    public MovieUser queryMovieUserById(@RequestParam("id")String id){
        return movieUserService.selectByPrimaryKey(id);
    }


    @RequestMapping(value = "/updateMovieUserById",method = RequestMethod.POST)
    public int updateMovieUserById(@RequestBody MovieUser movieUser){
        return movieUserService.updateByPrimaryKeySelective(movieUser);
    }


    @RequestMapping(value = "/queryMovieUserCountByMovieIdFromState",method = RequestMethod.GET)
    public int queryMovieUserCountByMovieIdFromState(@RequestParam("movieId")String movieId, @RequestParam("state")String state){

        Example example=new Example(MovieUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("movieId",movieId);
        criteria.andEqualTo("state",state);
        return movieUserService.selectCountByExample(example);
    }



    @RequestMapping(value = "/queryShortCommandByMovieId",method = RequestMethod.GET)
    public List<MovieUserCommand> queryShortCommandByMovieId(@RequestParam("movieId")String movieId){
        return movieUserService.queryShortCommandByMovieId(movieId);
    }
}
