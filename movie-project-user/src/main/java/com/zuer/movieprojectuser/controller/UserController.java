package com.zuer.movieprojectuser.controller;


import com.google.common.base.Preconditions;
import com.zuer.movieprojectuser.entity.Status;
import com.zuer.movieprojectuser.entity.User;
import com.zuer.movieprojectuser.err.HippoServiceException;
import com.zuer.movieprojectuser.service.UserService;
import com.zuer.movieprojectuser.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/UserService")
public class UserController {

    @Autowired
    UserService userService;

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/queryUser",method = RequestMethod.POST)
    public Map<String,Object> queryUser(@RequestBody Map<String,Object>param)throws Exception {
        try {
            Map<String,Object> mapResult=new HashMap<>();
            Map<String,Object> map=new HashMap<>();
            map.put("userName","");
            List<User> userList=userService.queryUser(map);
            mapResult.put("list",userList);
            return mapResult;
        }catch (Exception e){
            throw  new Exception(e);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String addAddressList(@RequestBody Map<String, Object> param) throws Exception {
        System.out.println("登录信息："+param);
        Object userCode=param.get("userCode");
        Object password=param.get("password");

        Preconditions.checkArgument(userCode != null&&!"".equals(userCode), "请输入登录账号");
        Preconditions.checkArgument(password != null&&!"".equals(password), "请输入密码");
        User user=login(new UsernamePasswordToken(String.valueOf(userCode), String.valueOf(password)));

        return JWTUtil.sign(user.getUserCode(), user.getPassword());//加密token
    }

    private User  login(AuthenticationToken token) throws  Exception{
        try{
            System.out.println("UserLoginController token="+token.getCredentials()+" " +token.getPrincipal());
            SecurityUtils.getSubject().login(token);
        }catch (UnknownAccountException e) {
            throw new HippoServiceException(401, "账号不存在");
        }catch (IncorrectCredentialsException e) {
            throw new HippoServiceException(402, "账号或密码错误");
        }catch (LockedAccountException e) {
            throw new HippoServiceException(403, "该账号被锁定，请联系管理员");
        }catch (AuthenticationException e) {
            throw new HippoServiceException(404, "账号认证失败");
        }
        return (User)SecurityUtils.getSubject().getPrincipal();
    }

    public User obtainByPrincipal(String userCode) {

        List<User> userList=userService.queryUserByUserCode(userCode);
        if(userList==null||userList.size()<=0){
            throw new IllegalArgumentException("cannot find user by userCode=" + userCode);
        }
        return userList.get(0);

    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/getCurrentUser",method = RequestMethod.POST)
    public User getCurrentUser(@RequestBody Map<String, Object> param)throws Exception{

        String token=(String)param.get("token");
        Preconditions.checkArgument(token != null&&!"".equals(token), "token cannot be null!");

        List<User> userList=userService.queryUserByUserCode(JWTUtil.getUsername(token));
        if(userList==null||userList.size()<=0){
            throw new IllegalArgumentException("cannot find user by userCode=" + JWTUtil.getUsername(token));
        }
        if(!JWTUtil.verify(token, userList.get(0).getUserCode(), userList.get(0).getPassword()))
            throw new HippoServiceException(401, "Token校验失败");
        if(Objects.equals(userList.get(0).getStatue(), Status.LOCKED))
            throw new HippoServiceException(401, "该账号被锁定，请联系管理员");

        return userList.get(0);
    }

    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public void logout(@RequestBody Map<String, Object> param)throws Exception{
        SecurityUtils.getSubject().logout();
    }

}
