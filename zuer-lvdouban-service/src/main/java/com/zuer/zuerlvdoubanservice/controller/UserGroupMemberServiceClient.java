package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.UserGroupMember;
import com.zuer.zuerlvdoubanservice.service.UserGroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/UserGroupMember")
public class UserGroupMemberServiceClient {

    @Autowired
    UserGroupMemberService userGroupMemberService;
    @RequestMapping(value = "/deleteUserGroupMemberByGroupId",method = RequestMethod.GET)
    public void deleteUserGroupMemberByGroupId(@RequestParam("groupId") String groupId){

        Example example=new Example(UserGroupMember.class);
        example.createCriteria().andEqualTo("groupId",groupId);
        userGroupMemberService.deleteByExample(example);
    }

    @RequestMapping(value = "/insertUserGroupMember",method = RequestMethod.POST)
    public void insertUserGroupMember(@RequestBody UserGroupMember userGroupMember){
        userGroupMemberService.insertSelective(userGroupMember);
    }
}
