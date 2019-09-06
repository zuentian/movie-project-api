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
import com.zuer.zuerlvdoubancommon.utils.TreeUtil;
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
        List<MenuTree> menuTreeList=createrMenuTree(menus,"");
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
        return createrMenuTree(menuList, "");
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
        menu=setMenuCrt(menu);
        menu=setMenuUpd(menu);
        return menuFeginService.insertMenu(menu);
    }

    private Menu setMenuCrt(Menu menu){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String hostIp = ClientUtil.getClientIp(request);
        String token=request.getHeader("Authorization");//获取token值，取得登陆的账号
        UserInfo userInfo=userFeginService.queryUserInfoByUserName(JWTUtil.getUsername(token));
        // 默认属性
        String[] fields = {"crtHost","crtTime","crtUser","crtName"};
        Field field = ReflectionUtils.getAccessibleField(menu, "crtTime");
        // 默认值
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{hostIp,new Date(),userInfo.getUsername(),userInfo.getName()};
        }
        // 填充默认属性值
        setDefaultValues(menu, fields, value);
        return menu;
    }

    private Menu setMenuUpd(Menu menu){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String hostIp = ClientUtil.getClientIp(request);
        String token=request.getHeader("Authorization");//获取token值，取得登陆的账号
        UserInfo userInfo=userFeginService.queryUserInfoByUserName(JWTUtil.getUsername(token));
        // 默认属性
        String[] fields = {"updHost","updTime","updUser","updName"};
        Field field = ReflectionUtils.getAccessibleField(menu, "updTime");
        // 默认值
        Object [] value = null;
        if(field!=null&&field.getType().equals(Date.class)){
            value = new Object []{hostIp,new Date(),userInfo.getUsername(),userInfo.getName()};
        }
        // 填充默认属性值
        setDefaultValues(menu, fields, value);
        return menu;
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

    @RequestMapping(value = "/updateMenu",method = RequestMethod.POST)
    @ResponseBody
    public int updateMenu(@RequestParam Map<String, Object> param) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Menu menu = mapper.readValue((String) param.get("menu"), Menu.class);
        menu=setMenuUpd(menu);
        return menuFeginService.updateMenuById(menu);
    }
}
