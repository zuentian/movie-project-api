package com.zuer.movielvdoubanservice.service.impl;

import com.zuer.movielvdoubancommon.entity.UserInfo;
import com.zuer.movielvdoubancommon.exception.UserInvalidException;
import com.zuer.movielvdoubancommon.jwt.JWTInfo;
import com.zuer.movielvdoubanservice.feignConfig.IUserService;
import com.zuer.movielvdoubanservice.jwtConfig.JwtAuthenticationRequest;
import com.zuer.movielvdoubanservice.jwtConfig.JwtTokenUtil;
import com.zuer.movielvdoubanservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthServiceImpl implements AuthService {
    private JwtTokenUtil jwtTokenUtil;
    private IUserService userService;

    @Autowired
    public AuthServiceImpl(
            JwtTokenUtil jwtTokenUtil,
            IUserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public String login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        UserInfo info = userService.validate(authenticationRequest);
        if (!StringUtils.isEmpty(info.getId())) {
            return jwtTokenUtil.generateToken(new JWTInfo(info.getUsername(), info.getId() + "", info.getName()));
        }
        throw new UserInvalidException("用户不存在或账户密码错误!");
    }

}
