package com.zuer.zuerlvdoubanauth.shiro;

import com.zuer.zuerlvdoubanauth.feginservice.UserFeignService;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserFeignService userFeignService;

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
        String usename = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        UserInfo userInfo = userFeignService.queryUserInfoByUserName(usename);
        if(userInfo==null||userInfo.getUsername()==null){
            throw new UnknownAccountException();
        }
        DefaultPasswordService defaultPasswordService=new DefaultPasswordService();

        System.out.println("获取用户凭证信息user：" + userInfo);
        //if (!defaultPasswordService.passwordsMatch(password, userInfo.getPassword()))
            //throw new IncorrectCredentialsException();

        //if (Objects.equals(userInfo.getStatus(), Status.LOCKED))
          //  throw new LockedAccountException();
        return new SimpleAuthenticationInfo(userInfo, password, getName());
    }

}
