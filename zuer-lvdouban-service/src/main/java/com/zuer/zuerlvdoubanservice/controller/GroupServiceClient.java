package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.Group;
import com.zuer.zuerlvdoubanservice.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
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


    @RequestMapping(value = "/queryGroupById",method = RequestMethod.GET)
    public Group queryGroupById(@RequestParam("id") String id){
        return groupService.selectByPrimaryKey(id);
    }


    @RequestMapping(value = "/updateGroupById",method = RequestMethod.POST)
    public int updateGroupById(@RequestBody Group group){
        return groupService.updateByPrimaryKeySelective(group);
    }


    @RequestMapping(value = "/queryGroupByParentIdCount",method = RequestMethod.GET)
    public int queryGroupByParentIdCount(@RequestParam("parentId") String parentId){
        Example example= new Example(Group.class);
        example.createCriteria().andEqualTo("parentId",parentId);
        return groupService.selectCountByExample(example);
    }

    @RequestMapping(value = "/deleteGroupById",method = RequestMethod.GET)
    public int deleteGroupById(@RequestParam("id")String id){
        return groupService.deleteByPrimaryKey(id);
    }


    @RequestMapping(value = "/queryGroupByUserId",method = RequestMethod.GET)
    public List<Group> queryGroupByUserId(@RequestParam("userId")String userId){
        return groupService.queryGroupByUserId(userId);
    }



}
