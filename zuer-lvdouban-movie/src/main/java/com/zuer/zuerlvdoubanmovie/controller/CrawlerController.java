package com.zuer.zuerlvdoubanmovie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zuer.zuerlvdoubancommon.entity.CrawlerAccount;
import com.zuer.zuerlvdoubanmovie.common.em.MovieInfoHtml;
import com.zuer.zuerlvdoubanmovie.common.em.MovieSyncFlag;
import com.zuer.zuerlvdoubanmovie.common.entity.CrawlerDbMovieSimpleInfo;
import com.zuer.zuerlvdoubanmovie.common.entity.CrawlerDbRequestInfo;
import com.zuer.zuerlvdoubanmovie.common.entity.CrawlerDbResponseInfo;
import com.zuer.zuerlvdoubanmovie.common.service.SimulateLoginService;
import com.zuer.zuerlvdoubanmovie.common.util.CleanHtml;
import com.zuer.zuerlvdoubanmovie.config.MapCache;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerAccountFeignService;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerMovieSyncInfoFeignService;
import org.apache.commons.codec.binary.Base64;
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

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

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
        Map<String,String> tagsData = new HashMap<String, String>();
        tagsData.put("type","movie");
        Connection.Response response= null ;

        if(cookies == null ){
            logger.info("-->>CrawlerController searchTags() cookies失效，重新登陆");
            Map<String,String> data = new HashMap<String,String>();
            data.put("name",account);
            data.put("password",password);
            data.put("remember","false");
            data.put("ticket","");
            data.put("ck","");
            //模拟登陆
            Connection.Response loginResponse = simulateLoginService.login(data,"DB_LOGIN_URL");
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

                resultMap.put("status",maps.get("status"));
                resultMap.put("description",maps.get("description"));
                logger.info("-->>CrawlerController searchTags() 登录账号网名 loginName=["+loginName+"]");

            }else{
                logger.info("-->>CrawlerController  登陆失败! 原因 description=["+maps.get("description")+"]");
                resultMap.put("status",maps.get("status"));
                resultMap.put("description",maps.get("description"));
                logger.info("-->>CrawlerController searchTags() 登录账号网名 loginName=["+loginName+"]");
            }
        }
        response = simulateLoginService.requestByGet(cookies,tagsData,"DB_SEARCH_TAG");
        Map resMap = (Map) JSON.parse(response.body());
        List<String> tags = (List) resMap.get("tags");
        resultMap.put("tags",tags);
        resultMap.put("loginName",loginName);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getDbMovieInfo", method = RequestMethod.POST)
    public List<CrawlerDbMovieSimpleInfo> getDbMovieInfo(@RequestBody CrawlerDbRequestInfo crawlerDbRequestInfo) throws Exception {
        logger.info("-->>CrawlerController getDbMovieInfo() start");
        Map<String, Object> dbLoginParam = (Map<String, Object>) MapCache.get("DBLOGINPARAM");
        Map<String, String> cookies = null;
        String loginName ="";
        Connection.Response response = null;
        Map map = JSONObject.parseObject(JSONObject.toJSONString(crawlerDbRequestInfo), Map.class);
        if(null != dbLoginParam){
            cookies = (Map<String, String>) dbLoginParam.get("cookies");
            loginName = (String) dbLoginParam.get("loginName");
            logger.info("-->>CrawlerController getDbMovieInfo() 爬虫带cookies获取豆瓣电影信息 loginName=["+loginName+"]");
            response = simulateLoginService.requestByGet(cookies,map,"DB_SEARCH_SUBJECTS");
        }else{
            logger.info("-->>CrawlerController getDbMovieInfo() 爬虫无cookies获取豆瓣电影信息 ");
            response = simulateLoginService.requestByGet(cookies,map,"DB_SEARCH_SUBJECTS");
        }

        Map resMap = (Map) JSON.parse(response.body());
        List<JSONObject> subjects =(List<JSONObject>)resMap.get("subjects");
        List<CrawlerDbMovieSimpleInfo> list =subjects.parallelStream().map(
                s->{
                    CrawlerDbMovieSimpleInfo c = JSONObject.toJavaObject(s,CrawlerDbMovieSimpleInfo.class);
                    try {
                        c.setBase64Photo(getBase64MoviePhoto(c.getCover()));
                        String syncFlag = getSyncFlagById(c.getId());
                        c.setSyncFlag(syncFlag);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return c;
                }).collect(Collectors.toList());
        return list;
    }

    @Autowired
    private CrawlerMovieSyncInfoFeignService crawlerMovieSyncInfoFeignService;

    private String getSyncFlagById(String id) {
        logger.info("-->>CrawlerController getSyncFlagById() id=["+id+"]");
        String syncFlag = crawlerMovieSyncInfoFeignService.getSyncFlagByIdFromCrawlerMovieSyncInfo(id);
        return StringUtils.isNotEmpty(syncFlag)?syncFlag: MovieSyncFlag.SYNC_0.getType();
    }


    public String getBase64MoviePhoto(String url) throws Exception {
        logger.info("-->>CrawlerController getBase64MoviePhoto() url=["+url+"]");
        Connection.Response response = null;
        URL src  = new URL(url);
        String base64Photo = "";
        try {
            String referer = src.getProtocol() + "://" + src.getHost();//防盗链
            response = Jsoup.connect(url).ignoreContentType(true).referrer(referer).execute();
            byte[] data = response.bodyAsBytes();
            Base64 base64 = new Base64();
            base64Photo = base64.encodeToString(data);

        } catch (Exception e) {
            throw new Exception("爬取图片失败！");
        }
        return base64Photo;
    }



    public void analysisByScoreHtml(String html) {
        Document document = Jsoup.parse(html);
    }

    public CrawlerDbResponseInfo analysisByHtml(String html) throws Exception {
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
        crawlerDbResponseInfo.setLengths(CleanHtml.splitToArray(split(infos.get(MovieInfoHtml.LENGTH.getType()))));
        //上映时间
        crawlerDbResponseInfo.setTimes(CleanHtml.splitToArray(split(infos.get(MovieInfoHtml.TIME.getType()))));
        //制片国家或地区
        crawlerDbResponseInfo.setAreas(CleanHtml.splitToArray(split(infos.get(MovieInfoHtml.AREA.getType()))));
        crawlerDbResponseInfo.setLanguages(CleanHtml.splitToArray(split(infos.get(MovieInfoHtml.LANGUAGE.getType()))));
        crawlerDbResponseInfo.setNameOthers(CleanHtml.splitToArray(split(infos.get(MovieInfoHtml.NAME_OTHER.getType()))));
        crawlerDbResponseInfo.setTypes(CleanHtml.splitToArray(split(infos.get(MovieInfoHtml.TYPE.getType()))));
        crawlerDbResponseInfo.setActors(CleanHtml.splitToArray(split(infos.get(MovieInfoHtml.ACTOR.getType()))));
        //评分
        String score = document.select("[property=v:average]").text().trim();
        crawlerDbResponseInfo.setScore(score);
        return crawlerDbResponseInfo;

    }

    private String[] split(String str){
        return str==null?null:str.split("/");
    }

    public static void main(String[] args) throws Exception {
        /*String xmlString ="";
        File file =new File("C:\\Users\\Zuer\\Desktop\\qqqi.txt");

            InputStream in = new FileInputStream(file);
            int flen = (int)file.length();
        byte[] strBuffer = new byte[flen];
            in.read(strBuffer, 0, flen);
                xmlString = new String(strBuffer);

        CrawlerController c = new CrawlerController();
        c.analysisByHtml(xmlString);*/
        /*Map<String,String> data = new HashMap<String,String>();
        data.put("name","1974900537");
        data.put("password","qq1974900537");
        data.put("remember","false");
        data.put("ticket","");
        data.put("ck","");
        Connection.Response loginResponse =login(data,"DB_LOGIN_URL");
        String loginHtml = loginResponse.body();
        Map maps = (Map) JSON.parse(loginHtml);
        System.out.println(maps);*/
    }

}
