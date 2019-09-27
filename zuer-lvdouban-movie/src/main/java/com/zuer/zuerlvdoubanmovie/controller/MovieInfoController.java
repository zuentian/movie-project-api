package com.zuer.zuerlvdoubanmovie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubancommon.entity.*;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubancommon.vo.MovieInfoExp;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieCountryFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieInfoFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieRelNameFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.MovieTypeFeignService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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



    @RequestMapping(value = "/queryMovieInfoByParam",method = RequestMethod.POST)
    public Map<String,Object> queryMovieInfoByParam(@RequestParam Map<String,Object> param) throws Exception {
        Map<String,Object> map=new HashMap<String,Object>();
        String name=param.get("param")==null?null:(String)param.get("param");
        map.put("name",name);

        String time1=param.get("startTime")==null?null:(String) param.get("startTime");
        if(StringUtils.isNotBlank(time1)){
            long startTime=Long.valueOf(time1);
            map.put("startTime",new Date(startTime));
        }
        String time2=param.get("endTime")==null?null:(String)param.get("endTime");
        if(StringUtils.isNotBlank(time2)){
            long endTime=Long.valueOf(time2);
            map.put("endTime",new Date(endTime));
        }
        String startScore=param.get("startScore")==null?null:(String)param.get("startScore");
        if(StringUtils.isNotBlank(startScore)){
            map.put("startScore",Double.valueOf(startScore));
        }

        String endScore=param.get("endScore")==null?null:(String)param.get("endScore");
        if(StringUtils.isNotBlank(endScore)){
            map.put("endScore",Double.valueOf(endScore));
        }

        int page=Integer.valueOf((String)param.get("page"));
        int limit=Integer.valueOf((String)param.get("limit"));
        int start=(page-1)*limit+1;
        int end=page*limit;
        map.put("start",start);
        map.put("end",end);

        String movieType=param.get("movieType")==null?null:(String)param.get("movieType");
        map.put("movieType",movieType);
        String movieCountry=param.get("movieCountry")==null?null:(String)param.get("movieCountry");
        map.put("movieCountry",movieCountry);

        Map<String,Object> resultMap=new HashMap<String, Object>();
        int count=movieInfoFeignService.queryMovieInfoByParamCount(map);
        resultMap.put("count",count);
        List<MovieInfoExp> movieInfoListExpList=new ArrayList<MovieInfoExp>();
        if(count>0){
            List<MovieInfo> movieInfoList =movieInfoFeignService.queryMovieInfoByParam(map);

            movieInfoListExpList=movieInfoList.parallelStream().map(movieInfo -> {
                MovieInfoExp movieInfoExp=new MovieInfoExp();
                BeanUtils.copyProperties(movieInfo,movieInfoExp);

                List<MovieCountry> movieCountryList=movieCountryFeignService.queryMovieCountryByMovieId(movieInfo.getId());

                List<MovieType> movieTypeList=movieTypeFeignService.queryMovieTypeByMovieId(movieInfo.getId());

                List<MovieRelName> movieRelNameList=movieRelNameFeignService.queryMovieRelNameByMovieId(movieInfo.getId());

                movieInfoExp.setMovieCountryList(movieCountryList);
                movieInfoExp.setMovieTypeList(movieTypeList);
                movieInfoExp.setMovieRelNameList(movieRelNameList);
                return movieInfoExp;
            }).collect(Collectors.toList());

        }
        resultMap.put("list",movieInfoListExpList);

        return resultMap;
    }

    @RequestMapping(value = "/queryMovieInfoById/{id}",method = RequestMethod.GET)
    public Map<String,Object> queryMovieInfoById(@PathVariable String id){

        Map<String,Object> resultMap=new HashMap<String, Object>();
        MovieInfo movieInfo=movieInfoFeignService.queryMovieInfoById(id);
        resultMap.put("movieInfo",movieInfo);

        List<MovieRelName> movieRelNameList=movieRelNameFeignService.queryMovieRelNameByMovieId(id);
        resultMap.put("movieRelNameList",movieRelNameList);

        List<MovieCountry> movieCountryList=movieCountryFeignService.queryMovieCountryByMovieId(id);
        resultMap.put("movieCountryList",movieCountryList);

        List<MovieType> movieTypeList=movieTypeFeignService.queryMovieTypeByMovieId(id);
        resultMap.put("movieTypeList",movieTypeList);
        return resultMap;
    }

}
