package com.zuer.zuerlvdoubancommon.vo;

import lombok.Data;

import java.util.List;

@Data
public class EntireUser {
    public String id;
    public String username;
    public String name;
    private String description;
    private String image;
    private List<PermissionInfo> menus;//菜单属性
    private List<PermissionInfo> elements;//功能属性
    private List<MenuTree> menuTrees;//菜单树状结构
    private List<RouterTree> routerTrees;//动态路由，测试阶段
}
