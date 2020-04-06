package com.zuer.zuerlvdoubanmovie.controller;

import com.zuer.zuerlvdoubancommon.entity.CrawlerAccount;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerAccountFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerUrlInfoFeignService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableAutoConfiguration
@RequestMapping(value = "/CrawlerController")
@RestController
@Transactional(rollbackFor = {Exception.class})
public class CrawlerController {
    private static final Logger logger= LoggerFactory.getLogger(CrawlerController.class);

    public static String USER_AGENT = "User-Agent";
    public static String USER_AGENT_VALUE = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";
    //public static String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";
    public static String CHARSET_CODE = "UTF-8";

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

        simulateLogin(account, password);

        return null;
    }

    private void simulateLogin(String account, String password) throws Exception {
        String DB_LOGIN_URL="";
        Map<String,String> data = new HashMap<>();
        data.put("name",account);
        data.put("password",password);
        data.put("remember","false");
        data.put("ticket","");
        data.put("ck","");
        Connection.Response login = null;
        try {
            login = Jsoup.connect(DB_LOGIN_URL)
                    .ignoreContentType(true) // 忽略类型验证
                    .followRedirects(false) // 禁止重定向
                    .postDataCharset("utf-8")
                    .header("Upgrade-Insecure-Requests","1")
                    .header("Accept","application/json")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .header("X-Requested-With","XMLHttpRequest")
                    .header(USER_AGENT,USER_AGENT_VALUE)
                    .data(data)
                    .method(Connection.Method.POST)
                    .execute();
        } catch (Exception e) {
            throw new Exception("登录初始化失败！");
        }
        login.charset(CHARSET_CODE);
        String loginHtml = login.body();
    }

}
