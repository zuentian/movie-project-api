package com.zuer.zuerlvdoubanmovie.feginservice;

import com.zuer.zuerlvdoubancommon.entity.CrawlerMovieSyncInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("zuer-lvdouban-service")
public interface CrawlerMovieSyncInfoFeignService {

    @RequestMapping(value = "/CrawlerMovieSyncInfo/getSyncFlagByIdFromCrawlerMovieSyncInfo",method = RequestMethod.GET)
    String getSyncFlagByIdFromCrawlerMovieSyncInfo(@RequestParam("id") String id);
    @RequestMapping(value = "/CrawlerMovieSyncInfo/insertCrawlerMovieSyncFlag",method = RequestMethod.POST)
    void insertCrawlerMovieSyncFlag(@RequestBody CrawlerMovieSyncInfo crawlerMovieSyncInfo);
}
