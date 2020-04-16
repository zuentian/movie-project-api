package com.zuer.zuerlvdoubanmovie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zuer.zuerlvdoubancommon.entity.CrawlerAccount;
import com.zuer.zuerlvdoubanmovie.common.em.MovieInfoHtml;
import com.zuer.zuerlvdoubanmovie.common.util.CleanHtml;
import com.zuer.zuerlvdoubanmovie.common.entity.CrawlerDbRequestInfo;
import com.zuer.zuerlvdoubanmovie.common.entity.CrawlerDbResponseInfo;
import com.zuer.zuerlvdoubanmovie.common.service.SimulateLoginService;
import com.zuer.zuerlvdoubanmovie.config.MapCache;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerAccountFeignService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private CrawlerAccountFeignService crawlerAccountFeignService;

    @RequestMapping(value = "/searchTags",method = RequestMethod.GET)
    public Map<String,Object> searchTags(@RequestParam Map<String, Object> param) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
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
        Map<String, String> cookies = null;
        String loginName = "";
        //先取缓存中的cookies
        Map<String, Object> dbLoginParam = (Map<String, Object>) MapCache.get("DBLOGINPARAM");
        if(null != dbLoginParam){
            if(StringUtils.isNotEmpty((String)dbLoginParam.get("account"))&&account.equals(dbLoginParam.get("account"))){
                Date date = (Date)dbLoginParam.get("time");
                long time = (new Date().getTime()-date.getTime())/(1000*60);
                if(time<30){
                    logger.info("-->>CrawlerController searchTags() cookies有效，跳过登陆");
                    cookies = (Map<String, String>) dbLoginParam.get("cookies");
                    loginName = (String) dbLoginParam.get("loginName");
                }
            }
        }
        if(cookies == null ){
            logger.info("-->>CrawlerController searchTags() cookies失效，重新登陆");
            //模拟登陆
            Connection.Response loginResponse = simulateLoginService.login(account,password,"DB_LOGIN_URL");
            String loginHtml = loginResponse.body();

            Map maps = (Map) JSON.parse(loginHtml);
            logger.info("-->>CrawlerController  登陆 status = ["+maps.get("status")+"]");
            if(maps!=null&&"success".equals(maps.get("status"))){
                logger.info("-->>CrawlerController  登陆成功!");
                cookies = loginResponse.cookies();
                loginName = (String) ((Map)((Map)maps.get("payload")).get("account_info")).get("name");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("cookies",cookies);
                map.put("time",new Date());
                map.put("account",account);
                map.put("loginName",loginName);
                MapCache.set("DBLOGINPARAM",map);
            }else{
                logger.info("-->>CrawlerController  登陆失败! 原因 description=["+maps.get("description")+"]");
                throw new Exception((String) maps.get("description"));
            }
        }
        logger.info("-->>CrawlerController searchTags() 登录账号网名 loginName=["+loginName+"]");
        Map<String,String> data = new HashMap<String, String>();
        data.put("type","movie");
        Connection.Response response = simulateLoginService.requestByGet(cookies,data,"DB_SEARCH_TAG");
        Map resMap = (Map) JSON.parse(response.body());
        List<String> tags = (List) resMap.get("tags");
        resultMap.put("tags",tags);
        resultMap.put("loginName",loginName);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getDbMovieInfo", method = RequestMethod.POST)
    public Map<String,Object> getDbMovieInfo(@RequestBody CrawlerDbRequestInfo crawlerDbRequestInfo) throws Exception {

        logger.info("-->>CrawlerController getDbMovieInfo() start");

        Map<String, Object> dbLoginParam = (Map<String, Object>) MapCache.get("DBLOGINPARAM");
        Map<String, String> cookies = null;
        String loginName ="";
        if(null != dbLoginParam){
            cookies = (Map<String, String>) dbLoginParam.get("cookies");
            loginName = (String) dbLoginParam.get("loginName");

        }else{
            throw new Exception("登录失效，请刷新页面！");
        }
        Map map = JSONObject.parseObject(JSONObject.toJSONString(crawlerDbRequestInfo), Map.class);
        Connection.Response response = simulateLoginService.requestByGet(cookies,map,"DB_SEARCH_SUBJECTS");
        Map resMap = (Map) JSON.parse(response.body());
        List<JSONObject> subjects = (List) resMap.get("subjects");
        if(subjects!=null&&subjects.size()>0){
            for(JSONObject jsonObject : subjects){
                String url = (String) jsonObject.get("url");
                logger.info("-->>CrawlerController getDbMovieInfo()  url=["+url+"]");
                Connection.Response responseinfo = simulateLoginService.requestByGetFromUrl(cookies,map,url);
                String html = responseinfo.body();
                //解析html获取信息
                analysisByHtml(html);
            }
        }
        return null;
    }

    public void analysisByHtml(String html) throws Exception {
        Document document = Jsoup.parse(html);
        CrawlerDbResponseInfo crawlerDbResponseInfo = new CrawlerDbResponseInfo();
        //电影名,包括制片地区的语言
        String movieNameHtml = document.select("[property=v:itemreviewed]").text().trim();
        String[] movieNames = movieNameHtml.split(" ", 2);//只会分割两个元素
        int movieNamesLen = movieNames.length;
        if (movieNamesLen == 1){
            crawlerDbResponseInfo.setMovieName(movieNames[0]);
        }else{
            crawlerDbResponseInfo.setMovieName(movieNames[0]);
            crawlerDbResponseInfo.setMovieLocalName(movieNames[1]);
        }
        //年份
        String year = document.getElementsByClass("year").text().trim().
                replace("(","").replace(")","");
        crawlerDbResponseInfo.setYear(year);
        String info = document.select("[id=info]").text();
        //处理html获取电影信息
        Map<String,String> infos = CleanHtml.cleanHtmlMovieInfo(info);
        //片长
        //crawlerDbResponseInfo.setLength(infos.get(MovieInfoHtml.LENGTH.getValue()).split("/"));



        System.out.println();

    }


    public static void main(String[] args) throws Exception {
        String xmlString ="";
        File file =new File("C:\\Users\\Zuer\\Desktop\\qqqi.txt");

            InputStream in = new FileInputStream(file);
            int flen = (int)file.length();
        byte[] strBuffer = new byte[flen];
            in.read(strBuffer, 0, flen);
                xmlString = new String(strBuffer);

        CrawlerController c = new CrawlerController();
        c.analysisByHtml(xmlString);
    }
}
