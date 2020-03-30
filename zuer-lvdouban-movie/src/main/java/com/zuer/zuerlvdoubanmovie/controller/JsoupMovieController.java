package com.zuer.zuerlvdoubanmovie.controller;

import com.alibaba.fastjson.JSON;
import com.zuer.zuerlvdoubancommon.vo.DbMovieInfo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@EnableAutoConfiguration
@RequestMapping(value = "/JsoupMovieController")
@RestController
@Transactional(rollbackFor = {Exception.class})
public class JsoupMovieController {

    //登录url
    public static String DB_LOGIN_URL = "https://accounts.douban.com/j/mobile/login/basic";
    //获取登录用户的观影量
    public static String DB_USER_MOVIE = "https://movie.douban.com/mine";
    //获取豆瓣官网的电影信息
    public static String DB_MOVIE_INFO = "https://movie.douban.com/j/search_subjects";

    public static String USER_AGENT = "User-Agent";
    public static String USER_AGENT_VALUE = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36";
    //public static String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";
    public static String CHARSET_CODE = "UTF-8";


    public static void main(String[] args) throws Exception {
        simulateLogin("16621051600", "qq1974900537");
    }

    private static void simulateLogin(String userName, String pwd) throws Exception {

        Map<String,String> data = new HashMap<>();
        data.put("name",userName);
        data.put("password",pwd);
        data.put("remember","false");
        data.put("ticket","");
        data.put("ck","");
        Connection.Response login = Jsoup.connect(DB_LOGIN_URL)
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
        login.charset(CHARSET_CODE);
        String loginHtml = login.body();
        Map maps = (Map) JSON.parse(loginHtml);
        if(maps!=null&&"success".equals(maps.get("status"))){
            // login 中已经获取到登录成功之后的cookies
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
            }
        }else{
            throw new Exception("登陆失败！");
        }
        return;
    }


    /**
     *
     * @param type 默认movie
     * @param tag  标签
     * @param sort 排序方式
     * @param limit 每页最大数
     * @param start 开始页数
     * @return
     */
    @RequestMapping(value = "/getDBMovieInfo", method = RequestMethod.POST)
    public DbMovieInfo getDBMovieInfo(String type,String tag,String sort,String limit,String start) throws Exception {

        Map<String,String> data = new HashMap<>();
        data.put("type",type);
        data.put("tag",tag);
        data.put("sort",sort);
        data.put("page_limit",limit);
        data.put("page_start",start);
        Connection.Response login = null;
        try {
            login = Jsoup.connect(DB_MOVIE_INFO)
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
        } catch (IOException e) {
            throw new Exception("获取豆瓣官网信息失败！");
        }
        login.charset(CHARSET_CODE);
        String loginHtml = login.body();
        return null;
    }

}
