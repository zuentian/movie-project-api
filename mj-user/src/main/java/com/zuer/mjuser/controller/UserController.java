package com.zuer.mjuser.controller;

import com.zuer.mjuser.entity.User;
import com.zuer.mjuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {



    @Autowired
    private DiscoveryClient discoveryClient;


    @Autowired
    private UserService userService;


    @GetMapping(value = "/list")
    public List<User> list(){

        ServiceInstance instance=discoveryClient.getLocalServiceInstance();
        //LOGGER.info("call user/list service  host is  "+instance.getHost()+"service_id is "+instance.getServiceId());
        return userService.getAllUser();
    }

    @GetMapping(value = "/login")
    public User login(@RequestParam String name, @RequestParam String password){

        User user=userService.login(name,password);
        return user;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        User result =userService.register(user);
        return result!=null?"success":"fail";
    }





    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id){

        User user =new User();
        user.setId(id);
        userService.writeOff(user);

        return "success";
    }

}
