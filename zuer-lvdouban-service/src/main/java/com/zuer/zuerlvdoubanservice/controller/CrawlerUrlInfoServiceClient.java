package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.CrawlerUrlInfo;
import com.zuer.zuerlvdoubancommon.utils.RowBoundUtil;
import com.zuer.zuerlvdoubanservice.service.CrawlerUrlInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/CrawlerUrlInfo")
public class CrawlerUrlInfoServiceClient {


    @Autowired
    private CrawlerUrlInfoService crawlerUrlInfoService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insert(@RequestBody CrawlerUrlInfo crawlerUrlInfo){
        return crawlerUrlInfoService.insert(crawlerUrlInfo);
    }
    @RequestMapping(value = "/queryPage",method = RequestMethod.POST)
    public Map<String,Object> queryPage( @RequestBody Map<String,Object> map,
                                         @RequestParam("pageSize") String pageSize,
                                         @RequestParam("pageIndex") String pageIndex){
        Example example=new Example(CrawlerUrlInfo.class);
        example.setOrderByClause("TYPE DESC");//实现排序
        Example.Criteria criteria = example.createCriteria();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            criteria.andLike(entry.getKey(), "%"+ entry.getValue()+"%");
        }
        RowBounds rowBounds = RowBoundUtil.getRowBounds(pageSize, pageIndex);
        List<CrawlerUrlInfo> lists = crawlerUrlInfoService.selectByExampleAndRowBounds(example, rowBounds);
        Map<String,Object> resultMap=new HashMap<String, Object>();
        resultMap.put("list",lists);
        int count = crawlerUrlInfoService.selectCountByExample(example);
        resultMap.put("count",count);
        return resultMap;
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.GET)
    public void deleteById(@RequestParam("id") String id){
        crawlerUrlInfoService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/updateById",method = RequestMethod.POST)
    public void updateById(@RequestBody CrawlerUrlInfo crawlerUrlInfo){
        crawlerUrlInfoService.updateByPrimaryKey(crawlerUrlInfo);
    }
    @RequestMapping(value = "/queryCrawlerUrlInfoById",method = RequestMethod.GET)
    public CrawlerUrlInfo queryCrawlerUrlInfoById(@RequestParam("id") String id){
        return crawlerUrlInfoService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/queryCrawlerUrlInfoByUrlName",method = RequestMethod.GET)
    public CrawlerUrlInfo queryCrawlerUrlInfoByUrlName(@RequestParam("urlName") String urlName){
        Example example=new Example(CrawlerUrlInfo.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("urlName",urlName);
        List<CrawlerUrlInfo> list=crawlerUrlInfoService.selectByExample(example);
        return null==list?null:list.get(0);
    }
}
