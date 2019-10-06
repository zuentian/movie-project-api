package com.zuer.zuerlvdoubanmovie.controller;

import com.zuer.zuerlvdoubancommon.entity.MovieInfo;
import com.zuer.zuerlvdoubancommon.entity.MovieUser;
import com.zuer.zuerlvdoubancommon.vo.DictValue;
import com.zuer.zuerlvdoubancommon.vo.MovieScoreSection;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieInfoFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieUserFeignService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //计算想看人数和看过人数
    public void calculateWatchCount(String movieId){

        int watchAfterNumber=movieUserFeignService.queryMovieUserCountByMovieIdFromState(movieId,"2");
        int watchBeforeNumber=movieUserFeignService.queryMovieUserCountByMovieIdFromState(movieId,"1");
        System.out.println("movieId["+movieId+"]看过人数watchAfterNumber["+watchAfterNumber+"]想看人数watchBeforeNumber["+watchBeforeNumber+"]");
        MovieInfo movieInfo=new MovieInfo();
        movieInfo.setId(movieId);
        movieInfo.setWatchAfterNumber(watchAfterNumber);
        movieInfo.setWatchBeforeNumber(watchBeforeNumber);
        movieInfoFeignService.updateMovieInfoById(movieInfo);

    }

    //计算评分人数
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

    public List<MovieScoreSection> getScoreSectionCount(String movieId) {
        List<MovieScoreSection> result=new ArrayList<MovieScoreSection>();
        //5星数量
        int count5=movieUserFeignService.queryMovieUserScoreCountByMovieIdAndScore(movieId,"5");
        //4星数量
        int count4=movieUserFeignService.queryMovieUserScoreCountByMovieIdAndScore(movieId,"4");
        //3星数量
        int count3=movieUserFeignService.queryMovieUserScoreCountByMovieIdAndScore(movieId,"3");
        //2星数量
        int count2=movieUserFeignService.queryMovieUserScoreCountByMovieIdAndScore(movieId,"2");
        //1星数量
        int count1=movieUserFeignService.queryMovieUserScoreCountByMovieIdAndScore(movieId,"1");

        int count=count1+count2+count3+count4+count5;
        System.out.println("评分分段：count=["+count+"] count1=["+count1+"] count2=["+count2+"] count3=["+count3+"] count4=["+count4+"] count5=["+count5+"] ");
        if(count>0){
            MovieScoreSection movieScoreSection5=new MovieScoreSection();
            movieScoreSection5.setScore("5星");
            movieScoreSection5.setPercentage(new BigDecimal(count5*100).divide(new BigDecimal(count),1,BigDecimal.ROUND_HALF_DOWN).doubleValue());
            result.add(movieScoreSection5);


            MovieScoreSection movieScoreSection4=new MovieScoreSection();
            movieScoreSection4.setScore("4星");
            movieScoreSection4.setPercentage(new BigDecimal(count4*100).divide(new BigDecimal(count),1,BigDecimal.ROUND_HALF_DOWN).doubleValue());
            result.add(movieScoreSection4);


            MovieScoreSection movieScoreSection3=new MovieScoreSection();
            movieScoreSection3.setScore("3星");
            movieScoreSection3.setPercentage(new BigDecimal(count3*100).divide(new BigDecimal(count),1,BigDecimal.ROUND_HALF_DOWN).doubleValue());
            result.add(movieScoreSection3);

            MovieScoreSection movieScoreSection2=new MovieScoreSection();
            movieScoreSection2.setScore("2星");
            movieScoreSection2.setPercentage(new BigDecimal(count2*100).divide(new BigDecimal(count),1,BigDecimal.ROUND_HALF_DOWN).doubleValue());
            result.add(movieScoreSection2);

            MovieScoreSection movieScoreSection1=new MovieScoreSection();
            movieScoreSection1.setScore("1星");
            movieScoreSection1.setPercentage(new BigDecimal(count1*100).divide(new BigDecimal(count),1,BigDecimal.ROUND_HALF_DOWN).doubleValue());
            result.add(movieScoreSection1);
        }
        return result;
    }

    public static void main(String[] args) {
        double a=new BigDecimal(0*100).divide(new BigDecimal(3),1,BigDecimal.ROUND_HALF_DOWN).doubleValue();
        System.out.println(a);
    }
}
