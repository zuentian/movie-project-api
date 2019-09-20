package com.zuer.zuerlvdoubanauth.controller;


import com.google.common.base.Preconditions;
import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubancommon.exception.UserInvalidException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*
登录控制
 */
@EnableAutoConfiguration
@RequestMapping(value = "/LoginController")
@RestController
public class LoginController {



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String createAuthenticationToken(@RequestBody Map<String,Object> param) throws Exception{
        try {
            Object username=param.get("username");
            Object password=param.get("password");
            Preconditions.checkArgument(username != null&&!"".equals(username), "请输入登录账号");
            Preconditions.checkArgument(password != null&&!"".equals(password), "请输入密码");
            UserInfo info=login(new UsernamePasswordToken(String.valueOf(username), String.valueOf(password)));
            String token=JWTUtil.sign(info.getUsername(), info.getPassword());//加密token
            return token;
        }catch (UserInvalidException e){
            throw new UserInvalidException(e.getMessage());
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    private UserInfo login(AuthenticationToken token) throws  Exception{
        try{
            System.out.println("UserLoginController token="+token.getCredentials()+" " +token.getPrincipal());
            SecurityUtils.getSubject().login(token);
        }catch (UnknownAccountException e) {
            throw new Exception("账号不存在");
        }catch (IncorrectCredentialsException e) {
            throw new Exception("账号或密码错误");
        }catch (LockedAccountException e) {
            throw new Exception("该账号被锁定，请联系管理员");
        }catch (AuthenticationException e) {
            throw new Exception("账号认证失败");
            //throw new HippoServiceException(404, "账号认证失败");
        }
        return (UserInfo)SecurityUtils.getSubject().getPrincipal();
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    public void logOut(){
        SecurityUtils.getSubject().logout();
    }
}
