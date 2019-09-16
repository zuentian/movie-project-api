package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.FeginService.DictFeignService;
import com.zuer.zuerlvdoubanauth.FeginService.MenuFeginService;
import com.zuer.zuerlvdoubanauth.FeginService.MenuGroupFeignService;
import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.Menu;
import com.zuer.zuerlvdoubancommon.entity.MenuGroup;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubancommon.utils.TreeUtil;
import com.zuer.zuerlvdoubancommon.vo.DictValue;
import com.zuer.zuerlvdoubancommon.vo.MenuTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/*
菜单
 */
@EnableAutoConfiguration
@RequestMapping(value = "/MenuController")
@RestController
public class MenuController {
    @Autowired
    private MenuFeginService menuFeginService;
    @Autowired
    private UserFeginService userFeginService;
    @Autowired
    private DictFeignService dictFeignService;

    private final static String MENU_TYPE="menu";
    private final static String GROUP_TYPE="group";



    @RequestMapping(value = "/queryAllMenu",method = RequestMethod.GET)
    public List<Menu> queryAllMenu(){
        List<Menu> menuList=menuFeginService.queryMenu();
        return menuList;
    }
    @RequestMapping(value = "/getMenuTree",method = RequestMethod.GET)
    @ResponseBody
    public List<MenuTree> getMenuTree(String token){
        UserInfo userInfo=userFeginService.queryUserInfoByUserName(JWTUtil.getUsername(token));
        List<Menu> menus=menuFeginService.getUserAuthorityMenuByUserId(userInfo.getId());

        String root="";
        List<DictValue> dictValueList=dictFeignService.queryDictByDictType("MENUROOT");
        if(dictValueList!=null&&dictValueList.size()>0){
            root=dictValueList.get(0).getLabel();
        }
        List<MenuTree> menuTreeList=createrMenuTree(menus,root);
        return menuTreeList;
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


    @RequestMapping(value = "/queryMenuTree",method = RequestMethod.POST)
    public List<MenuTree> queryMenuTree(@RequestParam Map<String, Object> param) {
        String title = param.get("title") == null ? null : (String) param.get("title");
        List<Menu> menuList = menuFeginService.queryMenuByTitle(title);
        String root="";
        List<DictValue> dictValueList=dictFeignService.queryDictByDictType("MENUROOT");
        if(dictValueList!=null&&dictValueList.size()>0){
            root=dictValueList.get(0).getLabel();
        }
        return createrMenuTree(menuList, root);
    }

    @RequestMapping(value = "/queryMenuById/{id}",method = RequestMethod.GET)
    public Menu queryMenuById(@PathVariable String id){
        return menuFeginService.queryMenuById(id);
    }

    @RequestMapping(value = "/addMenu",method = RequestMethod.POST)
    @ResponseBody
    public int addMenu(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Menu menu = mapper.readValue((String) param.get("menu"), Menu.class);

        String uuid= UUID.randomUUID().toString();
        menu.setId(uuid);
        EntityUtils.setCreatAndUpdatInfo(menu);
        return menuFeginService.insertMenu(menu);
    }

    @RequestMapping(value = "/updateMenu",method = RequestMethod.POST)
    @ResponseBody
    public int updateMenu(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Menu menu = mapper.readValue((String) param.get("menu"), Menu.class);
        EntityUtils.setUpdatedInfo(menu);
        return menuFeginService.updateMenuById(menu);
    }

    @RequestMapping(value = "/deleteMenuById/{id}",method = RequestMethod.GET)
    public int deleteMenuById(@PathVariable String id) throws Exception{
        return menuFeginService.deleteMenuById(id);
    }
    @RequestMapping(value = "/queryMenuByParentIdCount/{parentId}",method = RequestMethod.GET)
    public int queryMenuByParentIdCount(@PathVariable String parentId) throws Exception{
        return menuFeginService.queryMenuByParentIdCount(parentId);
    }

    @Autowired
    private MenuGroupFeignService menuGroupFeignService;


    @RequestMapping(value = "/updateMenuGroupByGroupId",method = RequestMethod.POST)
    @ResponseBody
    public void updateMenuGroupByGroupId(@RequestParam Map<String, Object> param) throws Exception {

        String groupId=param.get("groupId")==null?null:(String)param.get("groupId");

        String menuTrees=param.get("menuTrees")==null?null:(String)param.get("menuTrees");
        String[] menus=null;
        if(StringUtils.isNotBlank(menuTrees)){
            menus=menuTrees.split(",");
        }

        menuGroupFeignService.deleteMenuGroupByGroupIdAndMenuType(groupId,MENU_TYPE);

        List<Menu> menuList = menuFeginService.queryMenu();
        Map<String, String> map = new HashMap<String, String>();
        for (Menu menu : menuList) {
            map.put(menu.getId(), menu.getParentId());
        }
        Set<String> relationMenus = new HashSet<String>();
        relationMenus.addAll(Arrays.asList(menus));

        String root="";
        List<DictValue> dictValueList=dictFeignService.queryDictByDictType("MENUROOT");
        if(dictValueList!=null&&dictValueList.size()>0){
            root=dictValueList.get(0).getLabel();
        }
        MenuGroup menuGroup = null;
        for (String menuId : menus) {
            findParentID(map, relationMenus, menuId,root);//需要父节点的id
        }
        for(String menuId:relationMenus){
            menuGroup=new MenuGroup(GROUP_TYPE,MENU_TYPE);
            menuGroup.setId(UUID.randomUUID().toString());
            menuGroup.setGroupId(groupId);
            menuGroup.setMenuId(menuId);
            menuGroup.setParentId("0");
            EntityUtils.setCreateInfo(menuGroup);
            menuGroupFeignService.insertMenuGroup(menuGroup);
        }
    }

    private void findParentID(Map<String, String> map, Set<String> relationMenus, String id,String root) {
        String parentId = map.get(id);
        if (root.equals(id)) {
            return;
        }
        relationMenus.add(parentId);
        findParentID(map, relationMenus, parentId,root);
    }
    @RequestMapping(value = "/queryMenuGroupByGroupId/{groupId}",method = RequestMethod.GET)
    public void queryMenuGroupByGroupId(@PathVariable String groupId) throws Exception{

        List<Menu> menuList=menuFeginService.queryMenuGroupByGroupIdAndGroupType(groupId,GROUP_TYPE);
        System.out.println("menuList-------------------"+menuList);
    }


}
