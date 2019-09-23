package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.ElementGroup;
import com.zuer.zuerlvdoubanservice.service.ElementGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/ElementGroup")
public class ElementGroupServiceClient {


    @Autowired
    private ElementGroupService elementGroupService;


    @RequestMapping(value = "/insertElementGroup",method = RequestMethod.POST)
    public int insertElementGroup(@RequestBody ElementGroup elementGroup){
        return elementGroupService.insertSelective(elementGroup);
    }


    @RequestMapping(value = "/deleteElementGroupByGroupIdAndElementId",method = RequestMethod.POST)
    public int deleteElementGroupByGroupIdAndElementId(@RequestBody ElementGroup elementGroup){

        return elementGroupService.delete(elementGroup);
    }


    @RequestMapping(value = "/queryElementGroupByGroupId",method = RequestMethod.GET)
    public List<ElementGroup> queryElementGroupByGroupId(@RequestParam("groupId") String groupId){
        Example example=new Example(ElementGroup.class);
        example.createCriteria().andEqualTo("groupId",groupId);
        return elementGroupService.selectByExample(example);
    }

    @RequestMapping(value = "/deleteElementGroupByGroupId",method = RequestMethod.GET)
    public void deleteElementGroupByGroupId(@RequestParam("groupId") String groupId){
        Example example=new Example(ElementGroup.class);
        example.createCriteria().andEqualTo("groupId",groupId);
        elementGroupService.deleteByExample(example);
    }
}
