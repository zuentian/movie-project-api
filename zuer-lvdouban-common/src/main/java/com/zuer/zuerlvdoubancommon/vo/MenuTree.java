package com.zuer.zuerlvdoubancommon.vo;

import lombok.Data;

@Data
public class MenuTree extends TreeNode implements Comparable<MenuTree>{
    String icon;
    String title;
    String href;
    String path;
    String component;
    String authority;
    String redirect;
    String code;
    String type;
    int orderNum;
    String label;

    @Override
    public int compareTo(MenuTree o) {
        return this.orderNum-o.orderNum;
    }
}
