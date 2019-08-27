package com.zuer.movieprojectuser.controller;


import com.zuer.movieprojectcommon.entity.Role;
import com.zuer.movieprojectuser.feignConfig.RoleFeginClient;
import com.zuer.movieprojectuser.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.SchemaOutputResolver;
import java.util.*;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/RoleInfoController")
public class RoleController {

    @Autowired
    RoleFeginClient roleFeginClient;


    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/queryRole",method = RequestMethod.POST)
    public Map<String,Object> queryRole(@RequestBody Map<String,Object> param){
        Map<String,Object> resultMap=new HashMap<>();
        int pageNum=(Integer)param.get("pageNum");
        int pageSize=(Integer)param.get("pageSize");
        String roleName=(String)param.get("roleName");
        String status=(String)param.get("status");

        int start=(pageNum-1)*pageSize+1;
        int end=pageNum*pageSize;
        Map<String ,Object> map=new HashMap<>();
        map.put("roleName",roleName);
        map.put("status",status);
        map.put("start",start);
        map.put("end",end);
        int count = roleFeginClient.queryRoleCount(map);
        List<Role> list=new ArrayList<>();
        if(count>0){
            list=roleFeginClient.queryRole(map);
        }
        resultMap.put("list",list);
        resultMap.put("count",count);
        return resultMap;
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/createRole",method = RequestMethod.POST)
    public void createRole(@RequestBody Map<String,Object> param) throws Exception{
        String roleName=(String)param.get("roleName");

        List<Role> roleList=roleFeginClient.queryRoleByRoleName(roleName);
        if(roleList!=null&&roleList.size()>0){
            throw new Exception("该角色已存在");
        }

        Role role=new Role();
        String uuid= UUID.randomUUID().toString();
        role.setRoleId(uuid);
        role.setRoleName(roleName);
        role.setStatus((String)param.get("status"));
        role.setCrtTime(DateUtils.getCurrentDateTime());
        role.setAltTime(DateUtils.getCurrentDateTime());
        roleFeginClient.createRole(role);
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/queryRoleByRoleId",method = RequestMethod.POST)
    public Role queryRoleByRoleId(@RequestBody Map<String,Object> param){
        String roleId=(String)param.get("roleId");
        return roleFeginClient.queryRoleByRoleId(roleId);
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/updateRoleByRoleId",method = RequestMethod.POST)
    public void updateRoleByRoleId(@RequestBody Map<String,Object> param){


        String roleId=(String)param.get("roleId");
        String status=(String)param.get("status");
        Role role=new Role();
        role.setRoleId(roleId);
        role.setStatus(status);
        role.setAltTime(DateUtils.getCurrentDateTime());
        roleFeginClient.updateRoleByRoleId(role);

    }

}
