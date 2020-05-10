package com.zuer.zuerlvdoubanmovie.feginservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("zuer-lvdouban-service")
public interface CrawlerMovieSyncInfoFeignService {

    @RequestMapping(value = "/CrawlerMovieSyncInfo/getSyncFlagByIdFromCrawlerMovieSyncInfo",method = RequestMethod.GET)
    String getSyncFlagByIdFromCrawlerMovieSyncInfo(@RequestParam("id") String id);
}
