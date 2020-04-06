package com.zuer.zuerlvdoubanmovie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubancommon.entity.CrawlerAccount;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubanmovie.feginservice.CrawlerAccountFeignService;
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
@RequestMapping(value = "/CrawlerAccountController")
@RestController
@Transactional(rollbackFor = {Exception.class})
public class CrawlerAccountController {
    private static final Logger logger= LoggerFactory.getLogger(CrawlerAccountController.class);
    @Autowired
    private CrawlerAccountFeignService crawlerAccountFeignService;

    @RequestMapping(value = "/addCrawlerAccount",method = RequestMethod.POST)
    public void addCrawlerAccount(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            CrawlerAccount crawlerAccount = mapper.readValue((String) param.get("crawlerAccountInfoAdd"), CrawlerAccount.class);
            crawlerAccount.setId(UUID.randomUUID().toString());
            EntityUtils.setCreatAndUpdatInfo(crawlerAccount);
            crawlerAccountFeignService.insert(crawlerAccount);
        } catch (Exception e) {
            throw new Exception("添加账户信息失败！");
        }
    }
    @RequestMapping(value = "/updateCrawlerAccountInfoById",method = RequestMethod.POST)
    public void updateCrawlerAccountInfoById(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            CrawlerAccount crawlerAccount = mapper.readValue((String) param.get("crawlerAccountInfoEdit"), CrawlerAccount.class);
            EntityUtils.setUpdatedInfo(crawlerAccount);
            crawlerAccountFeignService.updateById(crawlerAccount);
        } catch (Exception e) {
            throw new Exception("修改账户信息失败！");
        }
    }

    @RequestMapping(value = "/queryPage",method = RequestMethod.POST)
    public Map<String,Object> queryPage(@RequestParam Map<String, Object> param) throws Exception {
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            String pageSize=(String)param.get("pageSize");
            String pageIndex=(String)param.get("currentPage");
            String web =param.get("web")==null?null:(String) param.get("web");
            if(StringUtils.isNotBlank(web)){
                map.put("web",web);
            }
            Map<String,Object> resultMap = crawlerAccountFeignService.queryPage(map,pageSize,pageIndex);
            return resultMap;

        }catch (Exception e){
            throw new Exception("查询账号信息失败！");
        }
    }

    @RequestMapping(value = "/queryById/{id}",method = RequestMethod.GET)
    public CrawlerAccount queryById(@PathVariable String id) throws Exception {
        return crawlerAccountFeignService.queryCrawlerAccountById(id);
    }

    @RequestMapping(value = "/deleteById/{id}",method = RequestMethod.GET)
    public void deleteById(@PathVariable String id) throws Exception {
        crawlerAccountFeignService.deleteById(id);
    }
}
