package com.zuer.zuerlvdoubancommon.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zuer.zuerlvdoubancommon.entity.Element;
import com.zuer.zuerlvdoubancommon.entity.Group;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EntireUser {
    public String id;
    public String username;
    public String name;
    private String description;
    private String nameBak;
    private String avatar;
    private String level;
    private List<Element> elements;//功能属性
    private List<MenuTree> menuTrees;//菜单树状结构
    private List<RouterTree> routerTrees;//动态路由
    private List<Group> groupList;//权限
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date crtTime;
}
