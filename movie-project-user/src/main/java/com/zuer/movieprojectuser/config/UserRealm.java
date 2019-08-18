package com.zuer.movieprojectuser.config;

import com.zuer.movieprojectuser.controller.UserController;
import com.zuer.movieprojectuser.entity.Status;
import com.zuer.movieprojectuser.entity.User;
import com.zuer.movieprojectuser.entity.UserType;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserController userController;

    //获取角色权限信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("获取角色权限信息：" + principalCollection);
        return new SimpleAuthorizationInfo();
    }

    //获取用户凭证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("获取用户凭证信息：" + authenticationToken);
        String userCode = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        User user = userController.obtainByPrincipal(userCode);
        DefaultPasswordService defaultPasswordService=new DefaultPasswordService();
        System.out.println("获取用户凭证信息user：" + user);
        if (!defaultPasswordService.passwordsMatch(password, user.getPassword()))
            throw new IncorrectCredentialsException();

        if (Objects.equals(user.getStatue(), Status.LOCKED))
            throw new LockedAccountException();
        return new SimpleAuthenticationInfo(user, password, getName());
    }

}
