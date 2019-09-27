package com.zuer.zuerlvdoubanmovie.feginservice;

import com.zuer.zuerlvdoubancommon.entity.MovieInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "zuer-lvdouban-service")
public interface MovieInfoFeignService {

    @RequestMapping(value = "/MovieInfo/insertMovieInfo",method = RequestMethod.POST)
    void insertMovieInfo(@RequestBody MovieInfo movieInfo) throws Exception;
    @RequestMapping(value = "/MovieInfo/queryMovieInfoByParam",method = RequestMethod.POST)
    List<MovieInfo> queryMovieInfoByParam(@RequestBody Map<String, Object> map);
    @RequestMapping(value = "/MovieInfo/queryMovieInfoByParamCount",method = RequestMethod.POST)
    int queryMovieInfoByParamCount(@RequestBody Map<String, Object> map);
    @RequestMapping(value = "/MovieInfo/queryMovieInfoById",method = RequestMethod.GET)
    MovieInfo queryMovieInfoById(@RequestParam("id") String id);
}
