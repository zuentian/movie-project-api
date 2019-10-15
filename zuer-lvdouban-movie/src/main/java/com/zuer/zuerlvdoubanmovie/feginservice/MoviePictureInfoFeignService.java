package com.zuer.zuerlvdoubanmovie.feginservice;

import com.zuer.zuerlvdoubancommon.entity.MoviePictureInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "zuer-lvdouban-service")
public interface MoviePictureInfoFeignService {

    @RequestMapping(value = "/MoviePictureInfo/insertMovieInfo",method = RequestMethod.POST)
    void insertMoviePictureInfo(@RequestBody  MoviePictureInfo moviePictureInfo);

    @RequestMapping(value = "/MoviePictureInfo/queryMoviePictureInfoByMovieIdFromSix",method = RequestMethod.GET)
    List<MoviePictureInfo> queryMoviePictureInfoByMovieIdFromSix(@RequestParam("movieId") String movieId);
    @RequestMapping(value = "/MoviePictureInfo/queryMoviePictureInfoByMovieIdCount",method = RequestMethod.GET)
    int queryMoviePictureInfoByMovieIdCount(@RequestParam("movieId") String movieId);

    @RequestMapping(value = "/MoviePictureInfo/queryMoviePictureInfoByMovieId",method = RequestMethod.GET)
    List<MoviePictureInfo>  queryMoviePictureInfoByMovieId(@RequestParam("movieId")  String movieId);

    @RequestMapping(value = "/MoviePictureInfo/deleteMoviePictureInfoById",method = RequestMethod.GET)
    void deleteMoviePictureInfoById(@RequestParam("id")  String id);

    @RequestMapping(value = "/MoviePictureInfo/queryMoviePictureByParam",method = RequestMethod.POST)
    Map<String,Object> queryMoviePictureByParam(@RequestBody  Map<String,Object> map);
    @RequestMapping(value = "/MoviePictureInfo/queryMoviePictureInfoById",method = RequestMethod.GET)
    MoviePictureInfo queryMoviePictureInfoById(@RequestParam("id") String id);

    @RequestMapping(value = "/MoviePictureInfo/queryMoviePictureInfoByMovieIdFromType",method = RequestMethod.GET)
    List<MoviePictureInfo> queryMoviePictureInfoByMovieIdFromType(@RequestParam("movieId")String movieId,@RequestParam("type") String type);
}
