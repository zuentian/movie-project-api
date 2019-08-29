package com.zuer.movielvdoubanservice.service;

import com.zuer.movielvdoubanservice.jwtConfig.JwtAuthenticationRequest;

public interface AuthService{

    String login(JwtAuthenticationRequest authenticationRequest) throws Exception;
}

