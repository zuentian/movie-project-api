package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.User;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubanservice.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/User")
public class UserController {

    @Autowired
    private UserService userService;
    //private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @RequestMapping(value = "/validate",method = RequestMethod.POST)
    UserInfo validate(@RequestBody Map<String,String> param) throws InvocationTargetException, IllegalAccessException {
        String username=param.get("username");
        String password=param.get("password");
        User user =new User();
        user.setUsername(username);
        user=userService.selectOne(user);
        System.out.println("userInfoList："+user);
        UserInfo userInfo=new UserInfo();
        if(user!=null){

            if(password.equals(user.getPassword())){
                //if (encoder.matches(password, user.getPassword())){
                BeanUtils.copyProperties(user,userInfo);
            }
        }
        return  userInfo;
    }
}
