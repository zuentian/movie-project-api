package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.Group;
import com.zuer.zuerlvdoubanservice.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/Group")
public class GroupServiceClient {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/insertGroup",method = RequestMethod.POST)
    public int insertGroup( @RequestBody Group group){
        return groupService.insertSelective(group);
    }

    @RequestMapping(value = "/queryGroupByGroupTypeId",method = RequestMethod.GET)
    public List<Group> queryGroupByGroupTypeId(@RequestParam("groupTypeId") String groupTypeId){
        Example example=new Example(Group.class);
        example.createCriteria().andEqualTo("groupTypeId",groupTypeId);
        return groupService.selectByExample(example);
    }
}
