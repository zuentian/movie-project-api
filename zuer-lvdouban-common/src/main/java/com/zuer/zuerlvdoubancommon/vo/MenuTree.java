package com.zuer.zuerlvdoubancommon.vo;

import lombok.Data;

@Data
public class MenuTree extends TreeNode {
    String icon;
    String title;
    String href;
    boolean spread = false;
    String path;
    String component;
    String authority;
    String redirect;
    String code;
    String type;

    String label;

    public MenuTree() {
    }

    public MenuTree(String id, String name, String parentId) {
        this.id = id;
        this.parentId = parentId;
        this.title = name;
        this.label = name;
    }
    public MenuTree(String id, String name, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.title = name;
        this.label = name;
    }
}
