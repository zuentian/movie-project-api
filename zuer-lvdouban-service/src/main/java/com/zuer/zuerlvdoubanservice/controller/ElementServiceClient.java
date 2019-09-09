package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.Element;
import com.zuer.zuerlvdoubancommon.utils.RowBoundUtil;
import com.zuer.zuerlvdoubanservice.service.ElementService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/Element")
public class ElementServiceClient {

    @Autowired
    private ElementService elementService;


    @RequestMapping(value = "/queryPageElement",method = RequestMethod.POST)
    public Map<String, Object> queryElementParam(@RequestParam("pageSize") String pageSize,
                                          @RequestParam("page") String page,
                                          @RequestBody Map<String, Object> map){
        Map<String,Object> resultMap=new HashMap<>();
        Example example=new Example(Element.class);
        example.setOrderByClause("CODE");//实现排序
        Example.Criteria criteria = example.createCriteria();
        String name=map.get("name")==null?null:(String)map.get("name");
        String menuId=map.get("menuId")==null?null:(String)map.get("menuId");
        if(StringUtils.isNotBlank(name)){
            criteria.andLike("name","%"+name+"%");
        }
        if(StringUtils.isNotBlank(menuId)){
            criteria.andEqualTo("menuId",menuId);
        }

        RowBounds rowBounds = RowBoundUtil.getRowBounds(pageSize, page);

        List<Element> elementList=elementService.selectByExampleAndRowBounds(example,rowBounds);
        resultMap.put("list",elementList);
        int count=elementService.selectCountByExample(example);
        resultMap.put("count",count);
        return resultMap;
    }

    @RequestMapping(value = "/insertElement",method = RequestMethod.POST)
    public int insertElement(@RequestBody Element element){
        return elementService.insertSelective(element);
    }
}
