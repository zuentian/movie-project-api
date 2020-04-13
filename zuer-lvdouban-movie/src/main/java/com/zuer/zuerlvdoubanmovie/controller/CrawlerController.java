package com.zuer.zuerlvdoubanmovie.controller;

import com.alibaba.fastjson.JSON;
import com.zuer.zuerlvdoubancommon.entity.CrawlerAccount;
import com.zuer.zuerlvdoubanmovie.common.SimulateLoginService;
import com.zuer.zuerlvdoubanmovie.config.MapCache;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerAccountFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerUrlInfoFeignService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@EnableAutoConfiguration
@RequestMapping(value = "/CrawlerController")
@RestController
@Transactional(rollbackFor = {Exception.class})
public class CrawlerController {
    private static final Logger logger= LoggerFactory.getLogger(CrawlerController.class);

    /*
     * @Resource是名称装配注入，指定哪个service即可
     * @Autowired是类型装配注入，如果是接入普通的接口(实现了多个类),需要搭配Qualifier使用，但对于不需要实现类的service就不用搭配（很奇怪）
     */
    //@Resource(name="SimulateLoginService")
    @Autowired
    @Qualifier("SimulateLoginService")
    private SimulateLoginService simulateLoginService;

    @Autowired
    private CrawlerUrlInfoFeignService crawlerUrlInfoFeignService;

    @Autowired
    private CrawlerAccountFeignService crawlerAccountFeignService;

    @RequestMapping(value = "/searchTags",method = RequestMethod.GET)
    public String searchTags(@RequestParam Map<String,Object> param) throws Exception {

        CrawlerAccount crawlerAccount=crawlerAccountFeignService.queryCrawlerAccountByWebAndFlag("dbmovie","1");
        if(crawlerAccount==null){
            throw new Exception("未配置有效的登录账号！");
        }
        String account = crawlerAccount.getAccount();
        String password = crawlerAccount.getPassword();
        if(StringUtils.isEmpty(account)){
            throw new Exception("未配置登录账号！");
        }
        if(StringUtils.isEmpty(password)){
            throw new Exception("未配置登录密码！");
        }
        logger.info("-->>CrawlerController searchTags() account=["+account+"] password=["+password+"]");
        Map<String, String> cookies ;
        String loginName = "";
        //先取缓存中的cookies
        Map<String, Object> dbLoginParam = (Map<String, Object>) MapCache.get("DBLOGINPARAM");
        if(null != dbLoginParam){
            if(StringUtils.isNotEmpty((String)dbLoginParam.get("account"))&&account.equals(dbLoginParam.get("account"))){
                Date date = (Date)dbLoginParam.get("time");
                long time = new Date().getTime()-date.getTime()/(1000*60);
                if(time<30){
                    logger.info("-->>CrawlerController searchTags() cookies有效，跳过登陆");
                    cookies = (Map<String, String>) MapCache.get("cookies");
                    loginName = (String) MapCache.get("loginName");
                }
            }
        }else {
            logger.info("-->>CrawlerController searchTags() cookies失效，重新登陆");
            //模拟登陆
            Connection.Response response = simulateLoginService.login(account,password,"DB_LOGIN_URL");
            String loginHtml = response.body();

            Map maps = (Map) JSON.parse(loginHtml);
            if(maps!=null&&"success".equals(maps.get("status"))){
                cookies = response.cookies();
                loginName = (String) ((Map)((Map)maps.get("payload")).get("account_info")).get("name");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("cookies",cookies);
                map.put("time",new Date());
                map.put("account",account);
                map.put("loginName",loginName);
                MapCache.set("DBLOGINPARAM",map);

                Map<String,String> data = new HashMap<String, String>();
                data.put("type","movie");
                simulateLoginService.requestByGet(cookies,data,"DB_SEARCH_TAG");
            }
        }
        logger.info("-->>CrawlerController searchTags() 登录账号网名 loginName=["+loginName+"]");
        return loginName;
    }

    public static void main(String[] args) throws ParseException {

    }
}
