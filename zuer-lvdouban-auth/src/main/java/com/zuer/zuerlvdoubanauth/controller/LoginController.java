package com.zuer.zuerlvdoubanauth.controller;


import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubanauth.jwtConfig.JwtAuthenticationRequest;
import com.zuer.zuerlvdoubanauth.jwtConfig.JwtTokenUtil;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubancommon.exception.UserInvalidException;
import com.zuer.zuerlvdoubancommon.jwt.JWTInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
登录控制
 */
@EnableAutoConfiguration
@RequestMapping(value = "/LoginController")
@RestController
public class LoginController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserFeginService userFeginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception{
        System.out.println(authenticationRequest.getUsername()+" require logging++++++++++++++++++=");
        try {
            String token = login(authenticationRequest);
            System.out.println("登录token+++++++++++="+token);
            return token;
        }catch (UserInvalidException e){
            throw new UserInvalidException(e.getMessage());
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public String login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        UserInfo info = userFeginService.validate(authenticationRequest);
        System.out.println("登录用户查询数据结果++++++++++++++++++++++"+info);
        if (!StringUtils.isEmpty(info.getId())) {
            return jwtTokenUtil.generateToken(new JWTInfo(info.getUsername(), info.getId() + "", info.getName()));
        }
        throw new UserInvalidException("用户不存在或账户密码错误!");
    }
}
