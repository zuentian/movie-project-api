package com.zuer.zuerlvdoubanauth.controller;

import com.zuer.zuerlvdoubanauth.FeginService.MenuFeginService;
import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.Menu;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubancommon.vo.EntireUser;
import com.zuer.zuerlvdoubancommon.vo.PermissionInfo;
import com.zuer.zuerlvdoubancommon.vo.QueryParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
用户
 */
@EnableAutoConfiguration
@RequestMapping(value = "/UserController")
@RestController
public class UserController {

    @Autowired
    private UserFeginService userFeginService;
    @Autowired
    private MenuFeginService menuFeginService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public EntireUser getUserInfo(String token) throws Exception {
        System.out.println("登陆之后token："+token);

        UserInfo userInfo=userFeginService.queryUserInfoByUserName(JWTUtil.getUsername(token));
        EntireUser entireUser=new EntireUser();
        BeanUtils.copyProperties(userInfo,entireUser);

        List<Menu> menus=menuFeginService.getUserAuthorityMenuByUserId(userInfo.getId());
        List<PermissionInfo> permissionInfos=getMenuPermission(menus);
        Stream<PermissionInfo> menusFilter = permissionInfos.parallelStream().filter((permission) -> {
            return permission.getType().equals("menu");
        });
        entireUser.setMenus(menusFilter.collect(Collectors.toList()));
        System.out.println("登陆之查询菜单和功能结果EntireUser=["+entireUser+"]");
        return entireUser;
    }

    private List<PermissionInfo> getMenuPermission(List<Menu> menus){
        List<PermissionInfo> permissionInfoList=new ArrayList<>();
        for(Menu menu:menus){
            if(StringUtils.isBlank(menu.getHref())){
                menu.setHref("/"+menu.getCode());
            }
            PermissionInfo info=new PermissionInfo();
            info.setCode(menu.getCode());
            info.setMenu(menu.getTitle());
            String url=menu.getHref();
            if(!url.startsWith("/")){
                info.setUrl("/"+url);
            }else{
                info.setUrl(url);
            }
            info.setType("menu");
            info.setName("访问");
            info.setMethod("GET");
            permissionInfoList.add(info);
        }
        return permissionInfoList;
    }

    /*
    查询用户列表
     */
    @RequestMapping(value = "/queryUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> queryUser(@RequestParam Map<String, Object> param){
        QueryParam queryParam=new QueryParam(param);
        System.out.println(queryParam);

        System.out.println(userFeginService.queryUserByQueryParam(queryParam));
        return null;
    }
}
