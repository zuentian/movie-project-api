package com.zuer.movielvdoubanservice.feignConfig;

import com.zuer.movielvdoubancommon.entity.UserInfo;
import com.zuer.movielvdoubanservice.configuration.FeignConfiguration;
import com.zuer.movielvdoubanservice.jwtConfig.JwtAuthenticationRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//@FeignClient(value = "ace-admin",configuration = FeignConfiguration.class)
public interface IUserService {

   // @RequestMapping(value = "/api/user/validate", method = RequestMethod.POST)
    public UserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest);
}


