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

    @RequestMapping(value = "/CrawlerAccount/queryCrawlerAccountByWebAndFlag",method = RequestMethod.GET)
    CrawlerAccount queryCrawlerAccountByWebAndFlag(@RequestParam("web") String web,
                                                   @RequestParam("flag") String flag)throws Exception;

    @RequestMapping(value = "/CrawlerAccount/insert",method = RequestMethod.POST)
    int insert(@RequestBody CrawlerAccount crawlerAccount) throws Exception;

    @RequestMapping(value = "/CrawlerAccount/queryPage",method = RequestMethod.POST)
    Map<String,Object> queryPage(@RequestBody Map<String,Object> map,
                                 @RequestParam("pageSize") String pageSize,
                                 @RequestParam("pageIndex") String pageIndex);
    @RequestMapping(value = "/CrawlerAccount/queryCrawlerAccountById",method = RequestMethod.GET)
    CrawlerAccount queryCrawlerAccountById(@RequestParam("id") String id);

    @RequestMapping(value = "/CrawlerAccount/updateById",method = RequestMethod.POST)
    void updateById(@RequestBody CrawlerAccount crawlerAccount);
    @RequestMapping(value = "/CrawlerAccount/deleteById",method = RequestMethod.GET)
    void deleteById(@RequestParam("id") String id);
}
