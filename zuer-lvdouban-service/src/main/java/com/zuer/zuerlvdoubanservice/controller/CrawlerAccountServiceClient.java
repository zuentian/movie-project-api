package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.CrawlerAccount;
import com.zuer.zuerlvdoubancommon.utils.RowBoundUtil;
import com.zuer.zuerlvdoubanservice.service.CrawlerAccountService;
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
@RequestMapping(value = "/CrawlerAccount")
public class CrawlerAccountServiceClient {


    @Autowired
    private CrawlerAccountService crawlerAccountService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insert(@RequestBody CrawlerAccount crawlerAccount){
        return crawlerAccountService.insert(crawlerAccount);
    }


    @RequestMapping(value = "/queryPage",method = RequestMethod.POST)
    public Map<String,Object> queryPage( @RequestBody Map<String,Object> map,
                                         @RequestParam("pageSize") String pageSize,
                                         @RequestParam("pageIndex") String pageIndex){
        Example example=new Example(CrawlerAccount.class);
        example.setOrderByClause("FLAG DESC");//实现排序
        Example.Criteria criteria = example.createCriteria();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            criteria.andLike(entry.getKey(), "%"+ entry.getValue()+"%");
        }
        RowBounds rowBounds = RowBoundUtil.getRowBounds(pageSize, pageIndex);
        List<CrawlerAccount> lists = crawlerAccountService.selectByExampleAndRowBounds(example, rowBounds);
        Map<String,Object> resultMap=new HashMap<String, Object>();
        resultMap.put("list",lists);
        int count = crawlerAccountService.selectCountByExample(example);
        resultMap.put("count",count);
        return resultMap;
    }
    @RequestMapping(value = "/queryCrawlerAccountById",method = RequestMethod.GET)
    public CrawlerAccount queryCrawlerAccountById(@RequestParam("id") String id){
        return crawlerAccountService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/updateById",method = RequestMethod.POST)
    public void updateById(@RequestBody CrawlerAccount crawlerAccount){
        crawlerAccountService.updateByPrimaryKey(crawlerAccount);
    }
    @RequestMapping(value = "/deleteById",method = RequestMethod.GET)
    public void deleteById(@RequestParam("id") String id){
        crawlerAccountService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/queryCrawlerAccountByWebAndFlag",method = RequestMethod.GET)
    public CrawlerAccount queryCrawlerAccountByWebAndFlag(@RequestParam("web") String web,
                                                   @RequestParam("flag") String flag)throws Exception{
        Example example = new Example(CrawlerAccount.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(web)){
            criteria.andEqualTo("web",web);
        }
        if(StringUtils.isNotBlank(flag)){
            criteria.andEqualTo("flag",flag);
        }
        List<CrawlerAccount> list=crawlerAccountService.selectByExample(example);
        return null!=list&&list.size()>0?list.get(0):null;
    }
}
