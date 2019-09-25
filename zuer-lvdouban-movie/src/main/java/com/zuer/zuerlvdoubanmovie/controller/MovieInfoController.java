package com.zuer.zuerlvdoubanmovie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubancommon.entity.MovieCountry;
import com.zuer.zuerlvdoubancommon.entity.MovieInfo;
import com.zuer.zuerlvdoubancommon.entity.MovieRelName;
import com.zuer.zuerlvdoubancommon.entity.MovieType;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieCountryFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieInfoFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieRelNameFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieTypeFeignService;
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

    @Autowired
    private MovieRelNameFeignService movieRelNameFeignService;

    @Autowired
    private MovieTypeFeignService movieTypeFeignService;

    @Autowired
    private MovieCountryFeignService movieCountryFeignService;

    @RequestMapping(value = "/insertMovieInfo",method = RequestMethod.POST)
    public void insertMovieInfo(@RequestParam Map<String,Object> param) throws Exception {
        String info=param.get("movieInfo")==null?null:(String) param.get("movieInfo");
        ObjectMapper mapper = new ObjectMapper();
        MovieInfo movieInfo= mapper.readValue(info, MovieInfo.class);
        String id=UUID.randomUUID().toString();
        movieInfo.setId(id);
        EntityUtils.setCreatAndUpdatInfo(movieInfo);
        movieInfoFeignService.insertMovieInfo(movieInfo);

        String movieRelName=param.get("movieRelName")==null?null:(String)param.get("movieRelName");
        if(movieRelName!=null){
            String[] movieRelNames = movieRelName.trim().split(",");
            for(String str:movieRelNames){
                MovieRelName movieRelNameNew=new MovieRelName();
                movieRelNameNew.setId(UUID.randomUUID().toString());
                movieRelNameNew.setMovieId(id);
                movieRelNameNew.setName(str);
                EntityUtils.setCreatAndUpdatInfo(movieRelNameNew);
                movieRelNameFeignService.insertMovieRelName(movieRelNameNew);
            }
        }

        String movieType=param.get("movieType")==null?null:(String)param.get("movieType");
        if(movieType!=null){
            String[] movieTypes = movieType.trim().split(",");
            for(String str:movieTypes){
                MovieType movieTypeNew=new MovieType();
                movieTypeNew.setId(UUID.randomUUID().toString());
                movieTypeNew.setMovieId(id);
                movieTypeNew.setType(str);
                EntityUtils.setCreatAndUpdatInfo(movieTypeNew);
                movieTypeFeignService.insertMovieType(movieTypeNew);
            }
        }
        String movieCountry=param.get("movieCountry")==null?null:(String)param.get("movieCountry");
        if(movieCountry!=null){
            String[] movieCountrys = movieCountry.trim().split(",");
            for(String str:movieCountrys){
                MovieCountry movieCountryNew =new MovieCountry();
                movieCountryNew.setId(UUID.randomUUID().toString());
                movieCountryNew.setMovieId(id);
                movieCountryNew.setCountryCode(str);
                EntityUtils.setCreatAndUpdatInfo(movieCountryNew);
                movieCountryFeignService.insertMovieCountry(movieCountryNew);
            }
        }
    }


}
