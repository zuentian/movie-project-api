package com.zuer.movieprojectuser.feignConfig;

import com.zuer.movieprojectcommon.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("movie-project-common")
public interface UserFeignClient {


    @RequestMapping(value = "/User/queryUser",method = RequestMethod.POST)
    List<User> queryUser(@RequestBody Map<String, Object> map);

    @RequestMapping(value = "/User/queryUserByUserCode",method = RequestMethod.GET)
    List<User> queryUserByUserCode(@RequestParam("userCode") String userCode);

    @RequestMapping(value = "/User/insertUser",method = RequestMethod.POST)
    void insertUser(User user);
}
