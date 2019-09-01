package com.zuer.zuerlvdoubanauth.FeginService;

import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "zuer-lvdouban-service")
public interface UserFeginService {

    @RequestMapping(value = "/User/queryUserInfoByUserName",method = RequestMethod.GET)
    UserInfo queryUserInfoByUserName(@RequestParam("username") String username) ;

}
