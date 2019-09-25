package com.zuer.zuerlvdoubanmovie.feginservice;

import com.zuer.zuerlvdoubancommon.entity.MovieType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "zuer-lvdouban-service")
public interface MovieTypeFeignService {


    @RequestMapping(value = "/MovieType/insertMovieType",method = RequestMethod.POST)
    void insertMovieType(@RequestBody MovieType movieType);
}
