package com.zuer.movieprojectcommon.service;


import com.zuer.movieprojectcommon.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Mapper
@RequestMapping(value = "/Role")
@RestController
@EnableFeignClients
@EnableAutoConfiguration
public interface RoleService {


    @RequestMapping(value = "/queryRole",method = RequestMethod.POST)
    List<Role> queryRole(@RequestBody Map<String,Object> map);

    @RequestMapping(value = "/queryRoleCount",method = RequestMethod.POST)
    int queryRoleCount(@RequestBody Map<String, Object> map);

    @RequestMapping(value = "/createRole",method = RequestMethod.POST)
    void createRole(@RequestBody Role role);

    @RequestMapping(value = "/queryRoleByRoleName",method = RequestMethod.GET)
    List<Role> queryRoleByRoleName(@RequestParam("roleName") String roleName);
}
