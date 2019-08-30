package com.zuer.movielvdoubanservice.controller;

import com.zuer.movielvdoubanservice.jwtConfig.JwtAuthenticationRequest;
import com.zuer.movielvdoubanservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "jwt")
//@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "token", method = RequestMethod.POST)
    public String createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        System.out.println(authenticationRequest.getUsername()+" require logging...");
        System.out.println(authService);
        final String token = authService.login(authenticationRequest);
        System.out.println(token);
        return token;
    }
}
