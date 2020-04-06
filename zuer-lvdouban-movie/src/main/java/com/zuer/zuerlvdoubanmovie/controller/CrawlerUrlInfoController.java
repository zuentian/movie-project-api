package com.zuer.zuerlvdoubanmovie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubancommon.entity.CrawlerUrlInfo;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerUrlInfoFeignService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EnableAutoConfiguration
@RequestMapping(value = "/CrawlerUrlInfoController")
@RestController
@Transactional(rollbackFor = {Exception.class})
public class CrawlerUrlInfoController {
    private static final Logger logger= LoggerFactory.getLogger(CrawlerUrlInfoController.class);
    @Autowired
    private CrawlerUrlInfoFeignService crawlerUrlInfoFeignService;

    @RequestMapping(value = "/addCrawlerUrlInfo",method = RequestMethod.POST)
    public void addCrawlerUrlInfo(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            CrawlerUrlInfo crawlerUrlInfo = mapper.readValue((String) param.get("crawlerUrlInfoAdd"), CrawlerUrlInfo.class);
            crawlerUrlInfo.setId(UUID.randomUUID().toString());
            EntityUtils.setCreatAndUpdatInfo(crawlerUrlInfo);
            crawlerUrlInfoFeignService.insert(crawlerUrlInfo);
        } catch (Exception e) {
            throw new Exception("添加网站URL信息失败！");
        }
    }
    @RequestMapping(value = "/queryPage",method = RequestMethod.POST)
    public Map<String,Object> queryPage(@RequestParam Map<String, Object> param) throws Exception {
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            String pageSize=(String)param.get("pageSize");
            String pageIndex=(String)param.get("currentPage");
            String typeName =param.get("typeName")==null?null:(String) param.get("typeName");
            if(StringUtils.isNotBlank(typeName)){
                map.put("typeName",typeName);
            }
            Map<String,Object> resultMap = crawlerUrlInfoFeignService.queryPage(map,pageSize,pageIndex);
            return resultMap;

        }catch (Exception e){
            throw new Exception("查询网站URL信息失败！");
        }
    }

    @RequestMapping(value = "/deleteById/{id}",method = RequestMethod.GET)
    public void deleteById(@PathVariable String id) throws Exception {
        crawlerUrlInfoFeignService.deleteById(id);
    }

    @RequestMapping(value = "/queryById/{id}",method = RequestMethod.GET)
    public CrawlerUrlInfo queryById(@PathVariable String id) throws Exception {
        return crawlerUrlInfoFeignService.queryCrawlerUrlInfoById(id);
    }
    @RequestMapping(value = "/updateCrawlerUrlInfoById",method = RequestMethod.POST)
    public void updateCrawlerUrlInfoById(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            CrawlerUrlInfo crawlerUrlInfo = mapper.readValue((String) param.get("crawlerUrlInfoEdit"), CrawlerUrlInfo.class);
            EntityUtils.setUpdatedInfo(crawlerUrlInfo);
            crawlerUrlInfoFeignService.updateById(crawlerUrlInfo);
        } catch (Exception e) {
            throw new Exception("修改网站URL信息失败！");
        }
    }

}
