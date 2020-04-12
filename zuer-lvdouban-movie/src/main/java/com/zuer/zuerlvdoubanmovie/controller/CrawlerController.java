package com.zuer.zuerlvdoubanmovie.controller;

import com.alibaba.fastjson.JSON;
import com.zuer.zuerlvdoubancommon.entity.CrawlerAccount;
import com.zuer.zuerlvdoubanmovie.common.SimulateLoginService;
import com.zuer.zuerlvdoubanmovie.config.MapCache;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerAccountFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerUrlInfoFeignService;
import org.jsoup.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
@EnableAutoConfiguration
@RequestMapping(value = "/CrawlerController")
@RestController
@Transactional(rollbackFor = {Exception.class})
public class CrawlerController {
    private static final Logger logger= LoggerFactory.getLogger(CrawlerController.class);

    @Resource(name="SimulateLoginService")
    private SimulateLoginService simulateLoginService;

    @Autowired
    private CrawlerUrlInfoFeignService crawlerUrlInfoFeignService;

    @Autowired
    private CrawlerAccountFeignService crawlerAccountFeignService;

    @RequestMapping(value = "/searchTags",method = RequestMethod.GET)
    public List<Object[]> searchTags(@RequestParam Map<String,Object> param) throws Exception {

        CrawlerAccount crawlerAccount=crawlerAccountFeignService.queryCrawlerAccountByWebAndFlag("dbmovie","1");
        String account = crawlerAccount.getAccount();
        String password = crawlerAccount.getPassword();
        logger.info("-->>CrawlerController searchTags() account=["+account+"] password=["+password+"]");
        Map<String, String> cookies = (Map<String, String>) MapCache.get("dbLoginCookies");
        if(null == cookies){
            //模拟登陆
            Connection.Response response = simulateLoginService.login(account,password,"DB_LOGIN_URL");
            String loginHtml = response.body();
            Map maps = (Map) JSON.parse(loginHtml);
            if(maps!=null&&"success".equals(maps.get("status"))){
                MapCache.set("dbLoginCookies",cookies);
            }
        }else {

        }

           /* // login 中已经获取到登录成功之后的cookies
            // 构造访问个人中心的请求
            Document document = Jsoup.connect(DB_USER_MOVIE)
                    //取出login对象里面的cookies
                    .cookies(login.cookies())
                    .get();
            if (document != null) {
                Elements elements = document.select(".article .pl a");
                for(Element e:elements){
                    String text=e.text();
                    System.out.println(text);
                }
            } else {
                throw new Exception("出错啦！！！！！");
            }*//*
        }else{
            throw new Exception("登陆失败！");
        }*/
        return null;
    }

}
