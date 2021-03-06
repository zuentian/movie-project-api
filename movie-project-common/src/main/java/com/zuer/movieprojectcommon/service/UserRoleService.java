package com.zuer.movieprojectcommon.service;

import com.zuer.movieprojectcommon.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Mapper
@RequestMapping(value = "/UserRole")
@RestController
@EnableFeignClients
@EnableAutoConfiguration
public interface UserRoleService {

    @RequestMapping(value = "/insertUserRole",method = RequestMethod.POST)
    void insertUserRole(@RequestBody UserRole userRole);

    @RequestMapping(value = "/deleteUserRoleByUserId",method = RequestMethod.GET)
    void deleteUserRoleByUserId(@RequestParam("userId") String userId);


    @RequestMapping(value = "/queryUserRoleByUserId",method = RequestMethod.GET)
    List<UserRole> queryUserRoleByUserId(@RequestParam("userId") String userId);
}
