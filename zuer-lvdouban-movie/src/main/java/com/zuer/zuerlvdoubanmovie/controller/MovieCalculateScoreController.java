package com.zuer.zuerlvdoubanmovie.controller;

import com.zuer.zuerlvdoubancommon.entity.MovieUser;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieInfoFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieUserFeignService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.List;

@EnableAutoConfiguration
@RestController
@Transactional(rollbackFor = {Exception.class})
/**
 * 计算电影的评分，更新电影信息表里的分数
 */
public class MovieCalculateScoreController {

    @Autowired
    private MovieUserFeignService movieUserFeignService;
    @Autowired
    private MovieInfoFeignService movieInfoFeignService;


    public void calculateScore(String movieId){

        List<MovieUser> movieUserList=movieUserFeignService.queryMovieUserByMovieIdFromState2(movieId);
        int totalScore=0;
        int personCount=0;
        for(MovieUser movieUser:movieUserList){
            if(StringUtils.isNotBlank(movieUser.getScore())&&Integer.valueOf(movieUser.getScore())>0){
                personCount++;//评分人数
                totalScore+=Integer.valueOf(movieUser.getScore())*2;
            }

        }
        String score = "0.0";
        if(personCount>0){
            DecimalFormat df = new DecimalFormat("0.0");
            float num =(float)totalScore/personCount;
            score = df.format(num);
        }

        movieInfoFeignService.updateMovieInfoByIdFromScore(movieId,score,personCount);
    }

}
