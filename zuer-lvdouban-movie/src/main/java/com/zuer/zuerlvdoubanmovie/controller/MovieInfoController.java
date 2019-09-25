package com.zuer.zuerlvdoubanmovie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubancommon.entity.MovieInfo;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieInfoFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@EnableAutoConfiguration
@RequestMapping(value = "/MovieInfoController")
@RestController
@Transactional(rollbackFor = {Exception.class})
public class MovieInfoController {

    @Autowired
    private MovieInfoFeignService movieInfoFeignService;



    @RequestMapping(value = "/insertMovieInfo",method = RequestMethod.POST)
    public void insertMovieInfo(@RequestParam Map<String,Object> param) throws Exception {
        String info=param.get("movieInfo")==null?null:(String) param.get("movieInfo");
        ObjectMapper mapper = new ObjectMapper();
        MovieInfo movieInfo= mapper.readValue(info, MovieInfo.class);
        movieInfo.setId(UUID.randomUUID().toString());
        EntityUtils.setCreatAndUpdatInfo(movieInfo);
        movieInfoFeignService.insertMovieInfo(movieInfo);

        String movieRelName=param.get("movieRelName")==null?null:(String)param.get("movieRelName");
        if(movieRelName!=null){
            String[] movieRelnames = movieRelName.trim().split(",");
            for(String str:movieRelnames){

            }
        }

        String movieType=param.get("movieType")==null?null:(String)param.get("movieType");
        if(movieType!=null){
            String[] movieTypes = movieRelName.trim().split(",");
            for(String str:movieTypes){

            }
        }
        String movieCountry=param.get("movieCountry")==null?null:(String)param.get("movieCountry");
        if(movieCountry!=null){
            String[] movieCountrys = movieRelName.trim().split(",");
            for(String str:movieCountrys){

            }
        }
    }


}
