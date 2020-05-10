package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubanservice.service.CrawlerMovieSyncInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
