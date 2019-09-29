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
import org.springframework.web.multipart.MultipartFile;

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
    public void insertMovieInfo(@RequestParam Map<String,Object> param,@RequestParam("files") MultipartFile[] files) throws Exception {
        System.out.println(files);
        String info=param.get("movieInfo")==null?null:(String) param.get("movieInfo");
        ObjectMapper mapper = new ObjectMapper();
        MovieInfo movieInfo= mapper.readValue(info, MovieInfo.class);
        String id=UUID.randomUUID().toString();
        movieInfo.setId(id);
        EntityUtils.setCreatAndUpdatInfo(movieInfo);
        //movieInfoFeignService.insertMovieInfo(movieInfo);

        String movieRelName=param.get("movieRelName")==null?null:(String)param.get("movieRelName");
        if(StringUtils.isNotBlank(movieRelName)){
            String[] movieRelNames = movieRelName.trim().split(",");
            for(String str:movieRelNames){
                MovieRelName movieRelNameNew=new MovieRelName();
                movieRelNameNew.setId(UUID.randomUUID().toString());
                movieRelNameNew.setMovieId(id);
                movieRelNameNew.setName(str);
                EntityUtils.setCreateInfo(movieRelNameNew);
                //insertMovieRelName(movieRelNameNew);
            }
        }

        String movieType=param.get("movieType")==null?null:(String)param.get("movieType");

        if(StringUtils.isNotBlank(movieType)){
            String[] movieTypes = movieType.trim().split(",");
            for(String str:movieTypes){
                MovieType movieTypeNew=new MovieType();
                movieTypeNew.setId(UUID.randomUUID().toString());
                movieTypeNew.setMovieId(id);
                movieTypeNew.setType(str);
                EntityUtils.setCreateInfo(movieTypeNew);
                //insertMovieType(movieTypeNew);

            }
        }
        String movieCountry=param.get("movieCountry")==null?null:(String)param.get("movieCountry");
        if(StringUtils.isNotBlank(movieCountry)){
            String[] movieCountrys = movieCountry.trim().split(",");
            for(String str:movieCountrys){
                MovieCountry movieCountryNew =new MovieCountry();
                movieCountryNew.setId(UUID.randomUUID().toString());
                movieCountryNew.setMovieId(id);
                movieCountryNew.setCountryCode(str);
                EntityUtils.setCreateInfo(movieCountryNew);
                //insertMovieCountry(movieCountryNew);

            }
        }
    }
    /*电影出品方国家地区数据插入*/
    private void insertMovieCountry(MovieCountry movieCountry) {
        movieCountryFeignService.insertMovieCountry(movieCountry);
    }

    /*电影类型数据插入*/
    private void insertMovieType(MovieType movieType) {
        movieTypeFeignService.insertMovieType(movieType);
    }

    /*电影相关人物数据插入*/
    private void insertMovieRelName(MovieRelName movieRelName) {
        movieRelNameFeignService.insertMovieRelName(movieRelName);
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

        Date date=movieInfo.getMovieShowTime();
        resultMap.put("movieShowTime",date.getTime());

        List<MovieRelName> movieRelNameList=movieRelNameFeignService.queryMovieRelNameByMovieId(id);
        resultMap.put("movieRelNameList",movieRelNameList);

        List<MovieCountry> movieCountryList=movieCountryFeignService.queryMovieCountryByMovieId(id);
        resultMap.put("movieCountryList",movieCountryList);

        List<MovieType> movieTypeList=movieTypeFeignService.queryMovieTypeByMovieId(id);
        resultMap.put("movieTypeList",movieTypeList);
        return resultMap;
    }


    @RequestMapping(value = "/updateMovieInfo",method = RequestMethod.POST)
    public void updateMovieInfo(@RequestParam Map<String,Object> param) throws Exception {
        String info=param.get("movieInfo")==null?null:(String) param.get("movieInfo");
        ObjectMapper mapper = new ObjectMapper();
        MovieInfo movieInfo= mapper.readValue(info, MovieInfo.class);
        EntityUtils.setUpdatedInfo(movieInfo);
        movieInfoFeignService.updateMovieInfoById(movieInfo);
        int count=0;

        count=movieRelNameFeignService.deleteMovieRelNameByMovieId(movieInfo.getId());
        if(count>=0){
            String movieRelName=param.get("movieRelName")==null?null:(String)param.get("movieRelName");
            if(movieRelName!=null){
                String[] movieRelNames = movieRelName.trim().split(",");
                for(String str:movieRelNames){
                    MovieRelName movieRelNameNew=new MovieRelName();
                    movieRelNameNew.setId(UUID.randomUUID().toString());
                    movieRelNameNew.setMovieId(movieInfo.getId());
                    movieRelNameNew.setName(str);
                    EntityUtils.setCreateInfo(movieRelNameNew);
                    insertMovieRelName(movieRelNameNew);
                }
            }
        }

        count=movieTypeFeignService.deleteMovieTypeByMovieId(movieInfo.getId());
        if(count>=0){
            String movieType=param.get("movieType")==null?null:(String)param.get("movieType");
            if(movieType!=null){
                String[] movieTypes = movieType.trim().split(",");
                for(String str:movieTypes){
                    MovieType movieTypeNew=new MovieType();
                    movieTypeNew.setId(UUID.randomUUID().toString());
                    movieTypeNew.setMovieId(movieInfo.getId());
                    movieTypeNew.setType(str);
                    EntityUtils.setCreateInfo(movieTypeNew);
                    insertMovieType(movieTypeNew);

                }
            }
        }


        count=movieCountryFeignService.deleteMovieCountryByMovieId(movieInfo.getId());
        if(count>=0){
            String movieCountry=param.get("movieCountry")==null?null:(String)param.get("movieCountry");
            if(movieCountry!=null){
                String[] movieCountrys = movieCountry.trim().split(",");
                for(String str:movieCountrys){
                    if(StringUtils.isNotBlank(str)){
                        MovieCountry movieCountryNew =new MovieCountry();
                        movieCountryNew.setId(UUID.randomUUID().toString());
                        movieCountryNew.setMovieId(movieInfo.getId());
                        movieCountryNew.setCountryCode(str);
                        EntityUtils.setCreateInfo(movieCountryNew);
                        insertMovieCountry(movieCountryNew);
                    }
                }
            }
        }

    }



    @RequestMapping(value = "/deleteMovieInfoById/{id}",method = RequestMethod.GET)
    public void deleteMovieInfoById(@PathVariable String id){
        movieInfoFeignService.deleteMovieInfoById(id);
        movieRelNameFeignService.deleteMovieRelNameByMovieId(id);//删除电影相关人物
        movieTypeFeignService.deleteMovieTypeByMovieId(id);//删除电影的类型
        movieCountryFeignService.deleteMovieCountryByMovieId(id);//删除电影的出品方国家地区
    }



}
