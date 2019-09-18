package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.FeginService.*;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.*;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubancommon.utils.TreeUtil;
import com.zuer.zuerlvdoubancommon.vo.*;
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

    @Autowired
    private DictFeignService dictFeignService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public EntireUser getUserInfo(String token) throws Exception {
        System.out.println("登陆之后token："+token);

        UserInfo userInfo=userFeginService.queryUserInfoByUserName(JWTUtil.getUsername(token));
        EntireUser entireUser=new EntireUser();
        BeanUtils.copyProperties(userInfo,entireUser);
        System.out.println("登陆之后id:"+userInfo.getId());

        String isAuthority="";

        List<DictValue> dictValueList=dictFeignService.queryDictByDictType("AUTHORITYFLAG");
        if(dictValueList!=null&&dictValueList.size()>0){
            isAuthority=dictValueList.get(0).getLabel();
        }
        List<Menu> menus=null;
        if("Y".equals(isAuthority)){
            //查询数据字典，如果AUTHORITYFLAG开启了权限控制
            menus=menuFeginService.getUserAuthorityMenuByUserId(userInfo.getId());
        }else{
            menus=menuFeginService.getUserMenuAllByUserId(userInfo.getId());
        }


        System.out.println("登陆之后menus:"+menus);
        List<PermissionInfo> permissionInfos=getMenuPermission(menus);


        //此处是刷选菜单列表中类型为menu,但是没有必要
        //permissionInfos = permissionInfos.parallelStream().filter((permission) -> {
            //return permission.getType().equals("menu");
        //}).collect(Collectors.toList());
        entireUser.setMenus(permissionInfos);

        //将获取的菜单整理成树状结构
        String root="";
        List<DictValue> dictValueListMenuRoot=dictFeignService.queryDictByDictType("MENUROOT");
        if(dictValueListMenuRoot!=null&&dictValueListMenuRoot.size()>0){
            root=dictValueListMenuRoot.get(0).getLabel();
        }
        List<MenuTree> menuTreeList=createrMenuTree(menus,root);
        entireUser.setMenuTrees(menuTreeList);

        List<RouterTree> routerTrees=createrRouterTree(menus,root);
        entireUser.setRouterTrees(routerTrees);

        System.out.println("登陆之查询菜单和功能结果EntireUser=["+entireUser+"]");
        return entireUser;
    }

    private List<MenuTree> createrMenuTree(List<Menu> menus, String root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (Menu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            node.setLabel(menu.getTitle());
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }

    private List<RouterTree> createrRouterTree(List<Menu> menus, String root) {
        List<RouterTree> trees = new ArrayList<RouterTree>();
        RouterTree node = null;
        for (Menu menu : menus) {
            node = new RouterTree();
            node.setComponent(menu.getHref());
            node.setPath(menu.getCode());
            node.setId(menu.getId());
            node.setParentId(menu.getParentId());
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
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
            //info.setType("menu");
            info.setType(menu.getType());
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
