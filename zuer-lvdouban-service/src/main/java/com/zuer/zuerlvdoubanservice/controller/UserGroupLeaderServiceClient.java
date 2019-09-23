package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.UserGroupLeader;
import com.zuer.zuerlvdoubanservice.service.UserGroupLeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/UserGroupLeader")
public class UserGroupLeaderServiceClient {

    @Autowired
    private UserGroupLeaderService userGroupLeaderService;
    @RequestMapping(value = "/deleteUserGroupLeaderByGroupId",method = RequestMethod.GET)
    public void deleteUserGroupLeaderByGroupId(@RequestParam("groupId") String groupId){
        Example example=new Example(UserGroupLeader.class);
        example.createCriteria().andEqualTo("groupId",groupId);
        userGroupLeaderService.deleteByExample(example);
    }


    @RequestMapping(value = "/insertUserGroupLeader",method = RequestMethod.POST)
    public void insertUserGroupLeader(@RequestBody UserGroupLeader userGroupLeader){
        userGroupLeaderService.insertSelective(userGroupLeader);
    }
}
