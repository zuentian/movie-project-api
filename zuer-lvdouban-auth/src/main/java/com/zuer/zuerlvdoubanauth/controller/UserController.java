package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.FeginService.MenuFeginService;
import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubanauth.FeginService.UserGroupLeaderFeignService;
import com.zuer.zuerlvdoubanauth.FeginService.UserGroupMemberFeignService;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.*;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubancommon.vo.EntireUser;
import com.zuer.zuerlvdoubancommon.vo.PermissionInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

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
        EntityUtils.setCreatAndUpdatInfo(user);
        return userFeginService.insertUser(user);

    }
    @RequestMapping(value = "/updateUserById",method = RequestMethod.POST)
    @ResponseBody
    public int updateUserById(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue((String) param.get("user"), User.class);
        EntityUtils.setUpdatedInfo(user);
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
    @RequestMapping(value = "/queryUserByGroupId/{groupId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryUserByGroupId(@PathVariable String groupId) throws Exception {

        Map<String,Object> resultMap=new HashMap<>();
        List<User> leaders=userFeginService.queryUserLeaderByGroupId(groupId);
        List<User> members=userFeginService.queryUserMemberByGroupId(groupId);
        resultMap.put("leaders",leaders);
        resultMap.put("members",members);
        return resultMap;
    }

    @RequestMapping(value = "/queryUserLikeUserNames",method = RequestMethod.POST)
    @ResponseBody
    public List<User> queryUserLikeUserNames(@RequestParam Map<String, Object> param) throws Exception {
        String name=param.get("name")==null?null:(String)param.get("name");
        List<User> list=userFeginService.queryUserLikeUserNames(name);
        return list;
    }

    @Autowired
    UserGroupLeaderFeignService userGroupLeaderFeignService;
    @Autowired
    UserGroupMemberFeignService userGroupMemberFeignService;

    @RequestMapping(value = "/updateUserByGroupId",method = RequestMethod.POST)
    @ResponseBody
    public void updateUserByGroupId(@RequestParam Map<String, Object> param) throws Exception {
        String groupId=param.get("groupId")==null?null:(String)param.get("groupId");
        String leaders=param.get("leaders")==null?null:(String)param.get("leaders");
        String members=param.get("members")==null?null:(String)param.get("members");
        userGroupLeaderFeignService.deleteUserGroupLeaderByGroupId(groupId);
        userGroupMemberFeignService.deleteUserGroupMemberByGroupId(groupId);

        if(!StringUtils.isEmpty(leaders)){
            String[] leader=leaders.split(",");
            for(String userId:leader){
                UserGroupLeader userGroupLeader=new UserGroupLeader();
                userGroupLeader.setGroupId(groupId);
                userGroupLeader.setUserId(userId);
                userGroupLeader.setId(UUID.randomUUID().toString());
                EntityUtils.setCreatAndUpdatInfo(userGroupLeader);
                userGroupLeaderFeignService.insertUserGroupLeader(userGroupLeader);
            }
        }

        if(!StringUtils.isEmpty(members)){
            String[] member=members.split(",");
            for(String userId:member){
                UserGroupMember userGroupMember=new UserGroupMember();
                userGroupMember.setGroupId(groupId);
                userGroupMember.setUserId(userId);
                userGroupMember.setId(UUID.randomUUID().toString());
                EntityUtils.setCreatAndUpdatInfo(userGroupMember);
                userGroupMemberFeignService.insertUserGroupMember(userGroupMember);
            }
        }
    }
}
