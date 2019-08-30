package com.zuer.zuerlvdoubanauth.FeginService;

import com.zuer.zuerlvdoubanauth.jwtConfig.JwtAuthenticationRequest;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "zuer-lvdouban-service")
public interface UserFeginService {

    @RequestMapping(value = "/User/validate",method = RequestMethod.POST)
    UserInfo validate(JwtAuthenticationRequest authenticationRequest) ;

}
