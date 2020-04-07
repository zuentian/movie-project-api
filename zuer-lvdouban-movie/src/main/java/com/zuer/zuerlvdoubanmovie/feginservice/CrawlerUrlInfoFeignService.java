package com.zuer.zuerlvdoubanmovie.feginservice;


import com.zuer.zuerlvdoubancommon.entity.CrawlerUrlInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("zuer-lvdouban-service")
public interface CrawlerUrlInfoFeignService {


    @RequestMapping(value = "/CrawlerUrlInfo/queryPage",method = RequestMethod.POST)
    Map<String,Object> queryPage(@RequestBody Map<String,Object> map,
                                 @RequestParam("pageSize") String pageSize,
                                 @RequestParam("pageIndex") String pageIndex);
    @RequestMapping(value = "/CrawlerUrlInfo/insert",method = RequestMethod.POST)
    int insert(@RequestBody CrawlerUrlInfo crawlerAccount) throws Exception;

    @RequestMapping(value = "/CrawlerUrlInfo/deleteById",method = RequestMethod.GET)
    void deleteById(@RequestParam("id") String id);

    @RequestMapping(value = "/CrawlerUrlInfo/queryCrawlerUrlInfoById",method = RequestMethod.GET)
    CrawlerUrlInfo queryCrawlerUrlInfoById(@RequestParam("id") String id);

    @RequestMapping(value = "/CrawlerUrlInfo/updateById",method = RequestMethod.POST)
    void updateById(@RequestBody CrawlerUrlInfo crawlerUrlInfo);

    @RequestMapping(value = "/CrawlerUrlInfo/queryCrawlerUrlInfoByUrlName",method = RequestMethod.GET)
    CrawlerUrlInfo queryCrawlerUrlInfoByUrlName(@RequestParam("urlName") String urlName);
}
