package com.zuer.zuerlvdoubanmovie.feginservice;


import com.zuer.zuerlvdoubancommon.entity.CrawlerAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("zuer-lvdouban-service")
public interface CrawlerAccountFeignService {

    @RequestMapping(value = "/CrawlerAccount/queryCrawlerAccountByWeb",method = RequestMethod.GET)
    CrawlerAccount queryCrawlerAccountByWeb(@RequestParam("web") String web)throws Exception;

    @RequestMapping(value = "/CrawlerAccount/insert",method = RequestMethod.POST)
    int insert(@RequestBody CrawlerAccount crawlerAccount) throws Exception;

    @RequestMapping(value = "/CrawlerAccount/queryPage",method = RequestMethod.POST)
    Map<String,Object> queryPage(@RequestBody Map<String,Object> map,
                                 @RequestParam("pageSize") String pageSize,
                                 @RequestParam("pageIndex") String pageIndex);
}
