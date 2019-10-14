package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.Element;
import com.zuer.zuerlvdoubancommon.entity.GroupType;
import com.zuer.zuerlvdoubancommon.entity.MoviePictureInfo;
import com.zuer.zuerlvdoubancommon.utils.RowBoundUtil;
import com.zuer.zuerlvdoubanservice.service.MoviePictureInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/MoviePictureInfo")
@Transactional(rollbackFor = Exception.class)
public class MoviePictureInfoServiceClient {

    @Autowired
    private MoviePictureInfoService moviePictureInfoService;

    @RequestMapping(value = "/insertMovieInfo",method = RequestMethod.POST)
    public void insertMoviePictureInfo(@RequestBody MoviePictureInfo moviePictureInfo){
        moviePictureInfoService.insertSelective(moviePictureInfo);
    }

    @RequestMapping(value = "/queryMoviePictureInfoByMovieIdFromSix",method = RequestMethod.GET)
    public List<MoviePictureInfo> queryMoviePictureInfoByMovieIdFromSix(@RequestParam("movieId") String movieId){
        return moviePictureInfoService.queryMoviePictureInfoByMovieIdFromSix(movieId);
    }
    @RequestMapping(value = "/queryMoviePictureInfoByMovieIdCount",method = RequestMethod.GET)
    public int queryMoviePictureInfoByMovieIdCount(@RequestParam("movieId") String movieId){
        Example example=new Example(MoviePictureInfo.class);
        example.createCriteria().andEqualTo("movieId",movieId);
        return moviePictureInfoService.selectCountByExample(example);
    }


    @RequestMapping(value = "/queryMoviePictureInfoByMovieId",method = RequestMethod.GET)
    public List<MoviePictureInfo> queryMoviePictureInfoByMovieId(@RequestParam("movieId")  String movieId){
        Example example=new Example(MoviePictureInfo.class);
        example.setOrderByClause("CRT_TIME DESC");//实现排序
        example.createCriteria().andEqualTo("movieId",movieId);
        return moviePictureInfoService.selectByExample(example);

    }


    @RequestMapping(value = "/deleteMoviePictureInfoById",method = RequestMethod.GET)
    public void deleteMoviePictureInfoById(@RequestParam("id")  String id){
        moviePictureInfoService.deleteByPrimaryKey(id);
    }


    @RequestMapping(value = "/queryMoviePictureByParam",method = RequestMethod.POST)
    public Map<String,Object> queryMoviePictureByParam(@RequestBody  Map<String,Object> map){
        Map<String,Object> resultMap=new HashMap<String, Object>();

        Example example=new Example(MoviePictureInfo.class);
        example.setOrderByClause("CRT_TIME DESC");//实现排序
        Example.Criteria criteria = example.createCriteria();
        String movieId=map.get("movieId")==null?null:(String)map.get("movieId");
        if(StringUtils.isNotBlank(movieId)){
            criteria.andLike("movieId",movieId);
        }
        String type=map.get("type")==null?null:(String)map.get("type");
        if(StringUtils.isNotBlank(type)){
            criteria.andLike("type",type);
        }
        String page=map.get("page")==null?null:(String)map.get("page");
        String pageSize=map.get("limit")==null?null:(String)map.get("limit");


        RowBounds rowBounds = RowBoundUtil.getRowBounds(pageSize, page);

        List<MoviePictureInfo> moviePictureInfoList=moviePictureInfoService.selectByExampleAndRowBounds(example,rowBounds);
        resultMap.put("list",moviePictureInfoList);

        int count=moviePictureInfoService.selectCountByExample(example);
        resultMap.put("count",count);

        return resultMap;
    }

    @RequestMapping(value = "/queryMoviePictureInfoById",method = RequestMethod.GET)
    public MoviePictureInfo queryMoviePictureInfoById(@RequestParam("id") String id){
        return moviePictureInfoService.selectByPrimaryKey(id);
    }
}
