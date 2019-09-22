package com.zuer.zuerlvdoubancommon.vo;

import com.zuer.zuerlvdoubancommon.entity.Element;
import lombok.Data;

import java.util.List;

@Data
public class EntireUser {
    public String id;
    public String username;
    public String name;
    private String description;
    private String image;
    private String nameBak;
    private List<Element> elements;//功能属性
    private List<MenuTree> menuTrees;//菜单树状结构
    private List<RouterTree> routerTrees;//动态路由
}
