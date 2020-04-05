package com.zuer.zuerlvdoubanmovie.controller;

import com.zuer.zuerlvdoubancommon.entity.CrawlerAccount;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerAccountFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerUrlInfoFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@EnableAutoConfiguration
@RequestMapping(value = "/CrawlerController")
@RestController
@Transactional(rollbackFor = {Exception.class})
public class CrawlerController {

    @Autowired
    private CrawlerUrlInfoFeignService crawlerUrlInfoFeignService;

    @Autowired
    private CrawlerAccountFeignService crawlerAccountFeignService;

    @RequestMapping(value = "/searchTags",method = RequestMethod.GET)
    public List<Object[]> searchTags(@RequestParam Map<String,Object> param) throws Exception {

        CrawlerAccount crawlerAccount=crawlerAccountFeignService.queryCrawlerAccountByWeb("dbmovie");

        return null;
    }

}
