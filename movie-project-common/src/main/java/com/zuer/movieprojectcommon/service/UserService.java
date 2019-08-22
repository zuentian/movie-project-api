package com.zuer.movieprojectcommon.service;

import com.zuer.movieprojectcommon.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Mapper
@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/User")
public interface UserService {


    @RequestMapping(value = "/queryUser",method = RequestMethod.POST)
    List<User> queryUser(@RequestBody Map<String,Object> map);

    @RequestMapping(value="/queryUserByUserCode",method = RequestMethod.GET)
    List<User> queryUserByUserCode(@RequestParam("userCode") String userCode);

    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    int insertUser(@RequestBody User user);

}
