package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.Group;
import com.zuer.zuerlvdoubanservice.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/Group")
public class GroupServiceClient {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/insertGroup",method = RequestMethod.POST)
    public int insertGroup(Group group){
        return groupService.insertSelective(group);
    }
}
