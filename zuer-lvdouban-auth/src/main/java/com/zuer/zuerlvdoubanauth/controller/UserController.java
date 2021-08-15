package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.feginservice.*;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.*;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubancommon.utils.TreeUtil;
import com.zuer.zuerlvdoubancommon.utils.UploadFile;
import com.zuer.zuerlvdoubancommon.vo.DictValue;
import com.zuer.zuerlvdoubancommon.vo.EntireUser;
import com.zuer.zuerlvdoubancommon.vo.MenuTree;
import com.zuer.zuerlvdoubancommon.vo.RouterTree;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/*
用户
 */
@EnableAutoConfiguration
@RequestMapping(value = "/UserController")
@PropertySource("classpath:avatarUpload.properties")
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private MenuFeignService menuFeignService;

    @Autowired
    private ElementFeignService elementFeignService;

    @Autowired
    private DictFeignService dictFeignService;

    @Autowired
    private GroupFeignService groupFeignService;

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    public EntireUser getUserInfo(String token) throws Exception {
        System.out.println("登陆之后token："+token);

        UserInfo userInfo=userFeignService.queryUserInfoByUserName(JWTUtil.getUsername(token));
        EntireUser entireUser=new EntireUser();
        BeanUtils.copyProperties(userInfo,entireUser);
        System.out.println("登陆之后id:"+userInfo.getId());

        String isAuthority="";

        List<DictValue> dictValueList=dictFeignService.queryDictByDictType("AUTHORITYFLAG");
        if(dictValueList!=null&&dictValueList.size()>0){
            isAuthority=dictValueList.get(0).getLabel();
        }
        List<Menu> menus=null;
        List<Element> elements=null;
        if("Y".equals(isAuthority)){
            //查询数据字典，如果AUTHORITYFLAG开启了权限控制
            menus=menuFeignService.getUserAuthorityMenuByUserId(userInfo.getId());
            elements=elementFeignService.getUserAuthorityElementByUserId(userInfo.getId());
        }else{
            menus=menuFeignService.getUserMenuAllByUserId(userInfo.getId());
            elements=elementFeignService.getUserElementAllByUserId(userInfo.getId());
        }
        if(menus==null||menus.size()<=0){
            throw new Exception("请为该用户分配权限！");
        }
        entireUser.setElements(elements);

        //将获取的菜单整理成树状结构
        String root="";
        List<DictValue> dictValueListMenuRoot=dictFeignService.queryDictByDictType("MENUROOT");
        if(dictValueListMenuRoot!=null&&dictValueListMenuRoot.size()>0){
            root=dictValueListMenuRoot.get(0).getLabel();
        }
        List<MenuTree> menuTreeList=createrMenuTree(menus,root);
        entireUser.setMenuTrees(menuTreeList);

        //获取roles
        List<Group> groupList=groupFeignService.queryGroupByUserId(userInfo.getId());
        entireUser.setGroupList(groupList);

        List<RouterTree> routerTrees=createrRouterTree(menus,root);
        //因为这是VUE的动态路由，所以开头的path前面都要写先加上“/”
        routerTrees=routerTrees.parallelStream().map(
                routerTree -> {
                    routerTree.setPath("/"+routerTree.getPath());
                    return routerTree;
                }
        ).collect(Collectors.toList());
        entireUser.setRouterTrees(routerTrees);
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
            node.setType(menu.getType());
            if("menu".equals(menu.getType())) {
                node.setComponent(menu.getHref());
            }
            node.setPath(menu.getCode());
            node.setId(menu.getId());
            node.setName(menu.getTitle());
            node.setParentId(menu.getParentId());
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }

    /*
    查询用户列表
     */

    @RequestMapping(value = "/queryUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object>  queryUser(@RequestParam Map<String, Object> param) throws Exception{

        try{
            Map<String,Object> map=new HashMap<String, Object>(16);
            String pageSize=(String)param.get("limit");
            String pageIndex=(String)param.get("page");
            String name =param.get("name")==null?null:(String) param.get("name");
            if(name!=null&&!"".equals(name)){
                map.put("name",name);
            }
            Map<String,Object> resultMap = userFeignService.queryUserByQueryParam(map,pageSize,pageIndex);
            return resultMap;

        }catch (Exception e){
            throw new Exception("查询数据字典失败！");
        }
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public void addUser(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user= mapper.readValue((String) param.get("user"), User.class);

        UserInfo userInfoOld=userFeignService.queryUserInfoByUserName(user.getUsername());
        if(userInfoOld!=null){
            throw new Exception("该用户已被创建！");
        }

        String uuid=UUID.randomUUID().toString();
        user.setId(uuid);

        DefaultPasswordService defaultPasswordService=new DefaultPasswordService();
        String password = defaultPasswordService.encryptPassword(user.getPassword());
        user.setPassword(password);

        List<DictValue> dictValueList=dictFeignService.queryDictByDictType("AVATARSYSTEM");
        if(dictValueList!=null){
            for(DictValue dictValue:dictValueList){
                if(user.getSex().equals(dictValue.getValue())){
                    user.setAvatar(File.separator+vueIp+dictValue.getLabel());
                    break;
                }
            }
        }

        EntityUtils.setCreatAndUpdatInfo(user);
        userFeignService.insertUser(user);

    }
    @RequestMapping(value = "/updateUserById",method = RequestMethod.POST)
    @ResponseBody
    public int updateUserById(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue((String) param.get("user"), User.class);
        EntityUtils.setUpdatedInfo(user);
        return userFeignService.updateUserById(user);
    }

    @RequestMapping(value = "/queryUserById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public User queryUserById(@PathVariable String id) throws Exception {
        return userFeignService.queryUserById(id);
    }
    @RequestMapping(value = "/deleteUserById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public int deleteUserById(@PathVariable String id) throws Exception {
        return userFeignService.deleteUserById(id);
    }
    @RequestMapping(value = "/queryUserByGroupId/{groupId}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> queryUserByGroupId(@PathVariable String groupId) throws Exception {

        Map<String,Object> resultMap=new HashMap<>(16);
        List<User> leaders=userFeignService.queryUserLeaderByGroupId(groupId);
        List<User> members=userFeignService.queryUserMemberByGroupId(groupId);
        resultMap.put("leaders",leaders);
        resultMap.put("members",members);
        return resultMap;
    }

    @RequestMapping(value = "/queryUserLikeUserNames",method = RequestMethod.POST)
    @ResponseBody
    public List<User> queryUserLikeUserNames(@RequestParam Map<String, Object> param) throws Exception {
        String name=param.get("name")==null?null:(String)param.get("name");
        List<User> list=userFeignService.queryUserLikeUserNames(name);
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

    @Value("${local.vueIp}")
    private String vueIp;

    @Value("${local.upload.images.path}")
    private String uploadImagesPath;

    @Value("${static.image.path}")
    private String rootPath;

    @RequestMapping(value="/avatarUpload", method= RequestMethod.POST)
    public String avatarUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        String id=request.getParameter("id");
        String url=userFeignService.getUserAvatarUrl(id);

        String datePath=getDatePath();
        logger.info("avatarUpload 头像上传的根路径:rootPath=["+rootPath+"] 文件的放置路径：uploadImagesPath=["+uploadImagesPath+"] 日期格式的文件路径：datePath=["+datePath+"]");

        String path=rootPath+uploadImagesPath+File.separator+datePath;

        logger.info("avatarUpload 头像上传全路径path=["+path+"]");

        if(url==null||UploadFile.deleteFile(rootPath+url)){
            User user=new User();
            user.setId(id);
            String fileName=UploadFile.uploadMultipartFile(file,UUID.randomUUID().toString(),path);
            user.setAvatar(File.separator+vueIp+ uploadImagesPath+File.separator+datePath+File.separator+fileName);
            user.setUrl(uploadImagesPath+File.separator+datePath+File.separator+fileName);
            logger.info("avatarUpload user.getAvatar=["+user.getAvatar()+"]");
            logger.info("avatarUpload user.getUrl=["+user.getUrl()+"]");
            userFeignService.updateUserAvatarById(user);
            return user.getAvatar();
        }
        return null;
    }


    /*
    获取带日期的路径
     */
    public String getDatePath(){

        Calendar cal = Calendar.getInstance();
        String year=cal.get(Calendar.YEAR)+"";
        String month=cal.get(Calendar.MONTH)+1<10?"0"+(cal.get(Calendar.MONTH)+1):(cal.get(Calendar.MONTH)+1)+"";
        String day=cal.get(Calendar.DAY_OF_MONTH)<10?"0"+cal.get(Calendar.DAY_OF_MONTH):cal.get(Calendar.DAY_OF_MONTH)+"";
        return year+ File.separator+month+ File.separator+day;


    }
}
