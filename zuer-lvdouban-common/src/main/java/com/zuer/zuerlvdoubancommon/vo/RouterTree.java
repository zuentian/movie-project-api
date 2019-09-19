package com.zuer.zuerlvdoubancommon.vo;

import lombok.Data;

@Data
public class RouterTree   extends  TreeNode{


    private String path;
    private String component;
    private String name;

}
