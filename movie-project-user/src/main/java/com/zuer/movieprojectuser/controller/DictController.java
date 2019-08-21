package com.zuer.movieprojectuser.controller;

import com.zuer.movieprojectcommon.entity.DictValue;
import com.zuer.movieprojectuser.feignConfig.DictFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableAutoConfiguration
@RequestMapping(value = "/DictController")
@RestController
public class DictController {
    @Autowired
    DictFeignClient dictFeignClient;
    @RequestMapping(value = "/queryDictByDictType",method = RequestMethod.POST)
    public Map<String,Object> queryDictByDictType(@RequestBody Map<String,Object> param){
        String dictType=param.get("dictType")==null?null:(String)param.get("dictType");
        List<DictValue> list=dictFeignClient.queryDictByDictType(dictType);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("list",list);
        return resultMap;
    }

}
