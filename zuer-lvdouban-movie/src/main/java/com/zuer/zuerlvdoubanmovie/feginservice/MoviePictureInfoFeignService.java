package com.zuer.zuerlvdoubanmovie.feginservice;

import com.zuer.zuerlvdoubancommon.entity.MoviePictureInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "zuer-lvdouban-service")
public interface MoviePictureInfoFeignService {

    @RequestMapping(value = "/MoviePictureInfo/insertMovieInfo",method = RequestMethod.POST)
    void insertMoviePictureInfo(@RequestBody  MoviePictureInfo moviePictureInfo);

    @RequestMapping(value = "/MoviePictureInfo/queryMoviePictureInfoByMovieIdFromSix",method = RequestMethod.GET)
    List<MoviePictureInfo> queryMoviePictureInfoByMovieIdFromSix(@RequestParam("movieId") String movieId);
    @RequestMapping(value = "/MoviePictureInfo/queryMoviePictureInfoByMovieIdCount",method = RequestMethod.GET)
    int queryMoviePictureInfoByMovieIdCount(@RequestParam("movieId") String movieId);
}
