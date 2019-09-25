package com.zuer.zuerlvdoubanmovie.feginservice;

import com.zuer.zuerlvdoubancommon.entity.MovieInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "zuer-lvdouban-service")
public interface MovieInfoFeignService {

    @RequestMapping(value = "/MovieInfo/insertMovieInfo",method = RequestMethod.POST)
    void insertMovieInfo(@RequestBody MovieInfo movieInfo) throws Exception;
}
