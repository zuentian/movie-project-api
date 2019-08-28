package com.zuer.movieprojectuser.feignConfig;


import com.zuer.movieprojectcommon.entity.Role;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("movie-project-common")
public interface RoleFeginClient {


    @RequestMapping(value = "/Role/queryRole",method = RequestMethod.POST)
    List<Role> queryRole(@RequestBody Map<String,Object> map);

    @RequestMapping(value = "/Role/queryRoleCount",method = RequestMethod.POST)
    int queryRoleCount(@RequestBody Map<String, Object> map);

    @RequestMapping(value = "/Role/createRole",method = RequestMethod.POST)
    void createRole(@RequestBody Role role);

    @RequestMapping(value = "/Role/queryRoleByRoleName",method = RequestMethod.GET)
    List<Role> queryRoleByRoleName(@RequestParam("roleName") String roleName);

    @RequestMapping(value = "/Role/queryRoleByRoleId",method = RequestMethod.GET)
    Role queryRoleByRoleId(@RequestParam("roleId") String roleId);

    @RequestMapping(value = "/Role/updateRoleByRoleId",method = RequestMethod.POST)
    void updateRoleByRoleId(@RequestBody Role role);

    @RequestMapping(value = "/Role/deleteRoleByRoleId",method = RequestMethod.GET)
    void deleteRoleByRoleId(@RequestParam("roleId") String roleId);

    @RequestMapping(value = "/Role/updateRoleToStatusByRoleId",method = RequestMethod.POST)
    void updateRoleToStatusByRoleId(@RequestBody Role role);

    @RequestMapping(value = "/Role/queryRoleByStatusAndRoleName",method = RequestMethod.POST)
    List<Role> queryRoleByStatusAndRoleName(@RequestBody Map<String, Object> map);
}
