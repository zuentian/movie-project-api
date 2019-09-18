package com.zuer.zuerlvdoubancommon.vo;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {


        TreeNode treeNode1=new TreeNode();
        treeNode1.setId("1111");
        treeNode1.setParentId("00000");

        List<TreeNode> list=new ArrayList<>();
        list.add(treeNode1);
        TreeNode treeNode2=new TreeNode();
        treeNode2.setId("2222333");
        treeNode2.setParentId("22222");
        list.add(treeNode2);



        TreeNode treeNode3=new TreeNode();
        treeNode3.setId("444444");
        treeNode3.setParentId("1111");
        list.add(treeNode3);
        TreeNode treeNode4=new TreeNode();
        treeNode4.setId("5555");
        treeNode4.setParentId("00000");
        list.add(treeNode4);

        list=bulid(list,"00000");
        System.out.println(1111);
        System.out.println(list);
    }

    public static <T extends TreeNode> List<T> bulid(List<T> treeNodes,String root) {

        List<T> trees = new ArrayList<T>();
        boolean flag=true;
        for (T treeNode : treeNodes) {

            //if (root.equals(treeNode.getParentId())) {
             //   trees.add(treeNode);
            //}

            for (T it : treeNodes) {
                if (it.getParentId()!=null&&it.getParentId().equals(treeNode.getId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.add(it);
                    flag=true;
                }else{
                    flag=false;
                }
            }
            if(!flag){
                trees.add(treeNode);
            }
        }
        return trees;
    }
}
