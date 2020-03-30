package com.zuer.zuerlvdoubancommon.vo;

import lombok.Data;

@Data
public class MenuTree extends TreeNode{
    String icon;
    String title;
    String href;
    String path;
    String component;
    String authority;
    String redirect;
    String code;
    String type;
    Integer orderNum;
    String label;
}
