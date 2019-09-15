package com.zuer.zuerlvdoubanauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuer.zuerlvdoubanauth.FeginService.DictFeignService;
import com.zuer.zuerlvdoubanauth.FeginService.MenuFeginService;
import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.Menu;
import com.zuer.zuerlvdoubancommon.entity.User;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubancommon.utils.ClientUtil;
import com.zuer.zuerlvdoubancommon.utils.EntityUtils;
import com.zuer.zuerlvdoubancommon.utils.ReflectionUtils;
import com.zuer.zuerlvdoubancommon.utils.TreeUtil;
import com.zuer.zuerlvdoubancommon.vo.DictValue;
import com.zuer.zuerlvdoubancommon.vo.MenuTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
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

}
