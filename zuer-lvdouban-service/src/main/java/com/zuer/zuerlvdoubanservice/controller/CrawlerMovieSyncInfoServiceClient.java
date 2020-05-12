package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.CrawlerMovieSyncInfo;
import com.zuer.zuerlvdoubancommon.entity.ElementGroup;
import com.zuer.zuerlvdoubanservice.service.CrawlerMovieSyncInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/CrawlerMovieSyncInfo")
public class CrawlerMovieSyncInfoServiceClient {
    @Autowired
    private CrawlerMovieSyncInfoService crawlerMovieSyncInfoService;

    @RequestMapping(value = "/getSyncFlagByIdFromCrawlerMovieSyncInfo",method = RequestMethod.GET)
    public String getSyncFlagByIdFromCrawlerMovieSyncInfo(@RequestParam("id") String id){
        return crawlerMovieSyncInfoService.getSyncFlagByIdFromCrawlerMovieSyncInfo(id);
    }

    @RequestMapping(value = "/insertCrawlerMovieSyncFlag",method = RequestMethod.POST)
    void insertCrawlerMovieSyncFlag(@RequestBody CrawlerMovieSyncInfo crawlerMovieSyncInfo){
        String id = crawlerMovieSyncInfo.getId();
        Example example=new Example(ElementGroup.class);
        example.createCriteria().andEqualTo("id",id);
        int count = crawlerMovieSyncInfoService.selectCountByExample(example);
        if(count>0){
            crawlerMovieSyncInfoService.updateByPrimaryKeySelective(crawlerMovieSyncInfo);
        }else {
            crawlerMovieSyncInfoService.insertSelective(crawlerMovieSyncInfo);
        }

    }
}
