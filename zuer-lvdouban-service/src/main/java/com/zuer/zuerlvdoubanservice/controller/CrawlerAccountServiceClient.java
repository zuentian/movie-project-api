package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.CrawlerAccount;
import com.zuer.zuerlvdoubancommon.utils.RowBoundUtil;
import com.zuer.zuerlvdoubanservice.service.CrawlerAccountService;
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
}
