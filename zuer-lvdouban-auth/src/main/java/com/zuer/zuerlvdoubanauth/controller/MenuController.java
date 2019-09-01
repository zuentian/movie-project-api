package com.zuer.zuerlvdoubanauth.controller;

import com.zuer.zuerlvdoubanauth.FeginService.MenuFeginService;
import com.zuer.zuerlvdoubanauth.FeginService.UserFeginService;
import com.zuer.zuerlvdoubanauth.jwt.JWTUtil;
import com.zuer.zuerlvdoubancommon.entity.Menu;
import com.zuer.zuerlvdoubancommon.entity.UserInfo;
import com.zuer.zuerlvdoubancommon.utils.TreeUtil;
import com.zuer.zuerlvdoubancommon.vo.MenuTree;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    @RequestMapping(value = "/queryAllMenu",method = RequestMethod.GET)
    public List<Menu> queryMenu(){
        List<Menu> menuList=menuFeginService.queryMenu();
        return menuList;
    }
    @RequestMapping(value = "/getMenuTree",method = RequestMethod.GET)
    @ResponseBody
    public List<MenuTree> getMenuTree(String token){
        UserInfo userInfo=userFeginService.queryUserInfoByUserName(JWTUtil.getUsername(token));
        List<Menu> menus=menuFeginService.getUserAuthorityMenuByUserId(userInfo.getId());
        List<MenuTree> menuTreeList=createrMenuTree(menus,"");
        System.out.println("获取菜单的树状结构menuTreeList："+menuTreeList);
        return menuTreeList;
    }

    private List<MenuTree> createrMenuTree(List<Menu> menus, String root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (Menu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }
}
