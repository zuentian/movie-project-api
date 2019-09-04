package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.User;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubanservice.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/User")
public class UserServiceClient  {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/queryUserInfoByUserName",method = RequestMethod.GET)
    public UserInfo queryUserInfoByUserName(@RequestParam("username") String username) {

        User user =new User();
        user.setUsername(username);
        user=userService.selectOne(user);
        UserInfo userInfo=null;
        if(user!=null){
            userInfo=new UserInfo();
            BeanUtils.copyProperties(user,userInfo);
        }
        return  userInfo;
    }


    @RequestMapping(value = "/queryUserByQueryParam",method = RequestMethod.POST)
    public List<User> queryUserByQueryParam( @RequestBody Map<String, Object> param) {
        List<User> list = userService.queryUserByQueryParam(param);
        System.out.println("+++++++++++++++++++++++"+list);
        return list;
    }


    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    public int insertUser(@RequestBody User user){

        return userService.insertSelective(user);
    }
}
