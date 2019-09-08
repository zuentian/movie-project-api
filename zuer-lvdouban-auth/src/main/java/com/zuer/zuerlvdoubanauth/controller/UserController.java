package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.FeginService.MenuFeginService;
import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.Menu;
import com.zuer.zuerlvdoubancommon.entity.User;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubancommon.utils.ClientUtil;
import com.zuer.zuerlvdoubancommon.utils.ReflectionUtils;
import com.zuer.zuerlvdoubancommon.vo.EntireUser;
import com.zuer.zuerlvdoubancommon.vo.PermissionInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
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
        System.out.println("登陆之后id:"+userInfo.getId());
        List<Menu> menus=menuFeginService.getUserAuthorityMenuByUserId(userInfo.getId());

        System.out.println("登陆之后menus:"+menus);
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
    public Map<String,Object>  queryUser(@RequestParam Map<String, Object> param) throws Exception{

        try{
            Map<String,Object> map=new HashMap<>();
            String pageSize=(String)param.get("limit");
            String pageIndex=(String)param.get("page");
            String name =param.get("name")==null?null:(String) param.get("name");
            if(name!=null&&!"".equals(name)){
                map.put("name",name);
            }
            Map<String,Object> resultMap = userFeginService.queryUserByQueryParam(map,pageSize,pageIndex);
            return resultMap;

        }catch (Exception e){
            throw new Exception("查询数据字典失败！");
        }
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public int addUser(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user= mapper.readValue((String) param.get("user"), User.class);

        UserInfo userInfoOld=userFeginService.queryUserInfoByUserName(user.getUsername());
        if(userInfoOld!=null){
            throw new Exception("该用户已被创建！");
        }

        String uuid=UUID.randomUUID().toString();
        user.setId(uuid);

        DefaultPasswordService defaultPasswordService=new DefaultPasswordService();
        String password = defaultPasswordService.encryptPassword(user.getPassword());
        user.setPassword(password);
        user=setUserCrt(user);
        user=setUserUpd(user);
        return userFeginService.insertUser(user);

    }
    @RequestMapping(value = "/updateUserById",method = RequestMethod.POST)
    @ResponseBody
    public int updateUserById(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue((String) param.get("user"), User.class);
        user=setUserUpd(user);
        return userFeginService.updateUserById(user);
    }

    @RequestMapping(value = "/queryUserById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User queryUserById(@PathVariable String id) throws Exception {
        return userFeginService.queryUserById(id);
    }
    @RequestMapping(value = "/deleteUserById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public int deleteUserById(@PathVariable String id) throws Exception {
        return userFeginService.deleteUserById(id);
    }
    /**
     * 依据对象的属性数组和值数组对对象的属性进行赋值
     *
     * @param entity 对象
     * @param fields 属性数组
     * @param value 值数组
     * @author 王浩彬
     */
    private static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
        for(int i=0;i<fields.length;i++){
            String field = fields[i];
            if(ReflectionUtils.hasField(entity, field)){
                ReflectionUtils.invokeSetter(entity, field, value[i]);
            }
        }
    }

    private User setUserCrt(User user){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String hostIp = ClientUtil.getClientIp(request);
        String token=request.getHeader("Authorization");//获取token值，取得登陆的账号
        UserInfo userInfo=userFeginService.queryUserInfoByUserName(JWTUtil.getUsername(token));
        // 默认属性
        String[] fields = {"crtHost","crtTime","crtUser","crtName"};
        Field field = ReflectionUtils.getAccessibleField(user, "crtTime");
        // 默认值
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{hostIp,new Date(),userInfo.getUsername(),userInfo.getName()};
        }
        // 填充默认属性值
        setDefaultValues(user, fields, value);
        return user;
    }

    private User setUserUpd(User user){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String hostIp = ClientUtil.getClientIp(request);
        String token=request.getHeader("Authorization");//获取token值，取得登陆的账号
        UserInfo userInfo=userFeginService.queryUserInfoByUserName(JWTUtil.getUsername(token));
        // 默认属性
        String[] fields = {"updHost","updTime","updUser","updName"};
        Field field = ReflectionUtils.getAccessibleField(user, "updTime");
        // 默认值
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{hostIp,new Date(),userInfo.getUsername(),userInfo.getName()};
        }
        // 填充默认属性值
        setDefaultValues(user, fields, value);
        return user;
    }
}
