package com.zuer.zuerlvdoubanmovie.common.impl;

import com.zuer.zuerlvdoubancommon.entity.CrawlerUrlInfo;
import com.zuer.zuerlvdoubanmovie.common.SimulateLoginService;
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
    public static String USER_AGENT_VALUE = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";
    //public static String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";
    public static String CHARSET_CODE = "UTF-8";

    @Autowired
    private CrawlerUrlInfoFeignService crawlerUrlInfoFeignService;

    public Connection.Response login(String account,String password,String urlCode) throws Exception {
        logger.info("-->>SimulateLoginServiceImpl login start");
        CrawlerUrlInfo crawlerUrlInfo = crawlerUrlInfoFeignService.queryCrawlerUrlInfoByUrlName(urlCode);

        Connection.Response login = null;
        if(crawlerUrlInfo!=null&&StringUtils.isNotBlank(crawlerUrlInfo.getUrl())){

            String DB_LOGIN_URL=crawlerUrlInfo.getUrl();
            Map<String,String> data = new HashMap<>();
            data.put("name",account);
            data.put("password",password);
            data.put("remember","false");
            data.put("ticket","");
            data.put("ck","");

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
            return login;

        }else {
            throw new Exception("没有配置有效的地址");
        }
    }

}