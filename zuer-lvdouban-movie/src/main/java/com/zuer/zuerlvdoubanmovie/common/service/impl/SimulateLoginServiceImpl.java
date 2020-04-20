package com.zuer.zuerlvdoubanmovie.common.service.impl;

import com.zuer.zuerlvdoubancommon.entity.CrawlerUrlInfo;
import com.zuer.zuerlvdoubanmovie.common.service.SimulateLoginService;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerUrlInfoFeignService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service("SimulateLoginService")
public class SimulateLoginServiceImpl implements SimulateLoginService {

    private static final Logger logger= LoggerFactory.getLogger(SimulateLoginServiceImpl.class);
    public static String USER_AGENT = "User-Agent";
    public static String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36";
    public static String CHARSET_CODE = "UTF-8";

    @Autowired
    private CrawlerUrlInfoFeignService crawlerUrlInfoFeignService;

    public Connection.Response login(Map<String,String> data,String urlCode) throws Exception {
        logger.info("-->>SimulateLoginServiceImpl login start urlCode=["+urlCode+"]");
        CrawlerUrlInfo crawlerUrlInfo = crawlerUrlInfoFeignService.queryCrawlerUrlInfoByUrlName(urlCode);

        Connection.Response login = null;
        if(crawlerUrlInfo!=null&&StringUtils.isNotBlank(crawlerUrlInfo.getUrl())){

            String url=crawlerUrlInfo.getUrl();
            logger.info("-->>SimulateLoginServiceImpl login start urlCode=["+urlCode+"] url=["+url+"]");

            try {
                login = Jsoup.connect(url)
                        .ignoreContentType(true) // 忽略类型验证
                        .followRedirects(false) // 禁止重定向
                        .postDataCharset("utf-8")
                        .header("Upgrade-Insecure-Requests","1")
                        .header("Accept","application/json")
                        .header("Content-Type","application/x-www-form-urlencoded")
                        .header("X-Requested-With","XMLHttpRequest")
                        .header(USER_AGENT,USER_AGENT_VALUE)
                        .header("Host","accounts.douban.com")
                        .header("Origin","https://accounts.douban.com")
                        .header("Referer","https://accounts.douban.com/passport/login")
                        .header("Sec-Fetch-Mode","cors")
                        .header("Sec-Fetch-Site","same-origin")
                        .header("Cookie","bid=SPuyS8ldU9s")//豆瓣登录需要加这个东西，这个东西具体是一个账号对应一个还是会过期，尚不明确。
                        .data(data)
                        .method(Connection.Method.POST)
                        .execute();
            } catch (Exception e) {
                throw new Exception("登录初始化失败！");
            }
            login.charset(CHARSET_CODE);
            return login;

        }else {
            throw new Exception("没有配置有效的请求地址");
        }
    }

    @Override
    public Connection.Response requestByGet(Map<String, String> cookies,Map<String,String> data, String urlCode) throws Exception {
        logger.info("-->>SimulateLoginServiceImpl requestByGet start urlCode=["+urlCode+"]");

        CrawlerUrlInfo crawlerUrlInfo = crawlerUrlInfoFeignService.queryCrawlerUrlInfoByUrlName(urlCode);
        if(crawlerUrlInfo!=null&&StringUtils.isNotBlank(crawlerUrlInfo.getUrl())){
            String url=crawlerUrlInfo.getUrl();
            logger.info("-->>SimulateLoginServiceImpl login start urlCode=["+urlCode+"] url=["+url+"]");
            Connection.Response response = null;
            if(cookies==null){
                response = Jsoup.connect(url)
                        .ignoreContentType(true) // 忽略类型验证
                        .followRedirects(false) // 禁止重定向
                        .postDataCharset("utf-8")
                        .header("Upgrade-Insecure-Requests","1")
                        .header("Accept","application/json")
                        .header("Content-Type","application/x-www-form-urlencoded")
                        .header("X-Requested-With","XMLHttpRequest")
                        .header(USER_AGENT,USER_AGENT_VALUE)
                        .data(data)
                        .method(Connection.Method.GET)
                        .execute();

            }else {
                response = Jsoup.connect(url)
                        .ignoreContentType(true) // 忽略类型验证
                        .followRedirects(false) // 禁止重定向
                        .postDataCharset("utf-8")
                        .header("Upgrade-Insecure-Requests","1")
                        .header("Accept","application/json")
                        .header("Content-Type","application/x-www-form-urlencoded")
                        .header("X-Requested-With","XMLHttpRequest")
                        .header(USER_AGENT,USER_AGENT_VALUE)
                        .cookies(cookies)
                        .data(data)
                        .method(Connection.Method.GET)
                        .execute();
            }
            return response;
        }else {
            throw new Exception("没有配置有效的请求地址");
        }
    }

    @Override
    public Connection.Response requestByGetFromUrl(Map<String, String> cookies,Map<String,String> data, String url) throws Exception {
        logger.info("-->>SimulateLoginServiceImpl requestByGet start url=["+url+"]");
        Connection.Response response = null;
        if(cookies==null){
            response = Jsoup.connect(url)
                    .ignoreContentType(true) // 忽略类型验证
                    .followRedirects(false) // 禁止重定向
                    .postDataCharset("utf-8")
                    .header("Upgrade-Insecure-Requests","1")
                    .header("Accept","application/json")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .header("X-Requested-With","XMLHttpRequest")
                    .header(USER_AGENT,USER_AGENT_VALUE)
                    .data(data)
                    .method(Connection.Method.GET)
                    .execute();
        }else {
            response = Jsoup.connect(url)
                    .ignoreContentType(true) // 忽略类型验证
                    .followRedirects(false) // 禁止重定向
                    .postDataCharset("utf-8")
                    .header("Upgrade-Insecure-Requests","1")
                    .header("Accept","application/json")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .header("X-Requested-With","XMLHttpRequest")
                    .header(USER_AGENT,USER_AGENT_VALUE)
                    .cookies(cookies)
                    .data(data)
                    .method(Connection.Method.GET)
                    .execute();
        }

        return response;

    }

}