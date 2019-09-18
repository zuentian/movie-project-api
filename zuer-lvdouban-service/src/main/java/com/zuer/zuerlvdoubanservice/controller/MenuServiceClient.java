package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.Menu;
import com.zuer.zuerlvdoubanservice.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/Menu")
@Transactional(rollbackFor = Exception.class)
public class MenuServiceClient {

    @Autowired
    private MenuService menuService;
    @RequestMapping(value = "/getUserAuthorityMenuByUserId",method = RequestMethod.GET)
    public List<Menu> getUserAuthorityMenuByUserId(@RequestParam("id") String id) throws Exception{
        try{
            List<Menu> menus=menuService.getUserAuthorityMenuByUserId(id);
            return menus;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @RequestMapping(value = "/getUserMenuAllByUserId",method = RequestMethod.GET)
    public List<Menu> getUserMenuAllByUserId(@RequestParam("id") String id)throws Exception{
        try{
            List<Menu> menus=menuService.selectAll();
            return menus;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @RequestMapping(value = "/queryMenu",method = RequestMethod.GET)
    public List<Menu> queryMenu(){
        return menuService.selectAll();
    }

    @RequestMapping(value = "/queryMenuByTitle",method = RequestMethod.GET)
    public List<Menu> queryMenuByTitle(@RequestParam(value="title", required = false) String title){
        Example example = new Example(Menu.class);
        if (StringUtils.isNotBlank(title)) {
            example.createCriteria().andLike("title", "%" + title + "%");
        }
        return menuService.selectByExample(example);
    }


    @RequestMapping(value = "/queryMenuById",method = RequestMethod.GET)
    public Menu queryMenuById(@RequestParam("id") String id){
        return menuService.selectByPrimaryKey(id);
    }


    @RequestMapping(value = "/insertMenu",method = RequestMethod.POST)
    public int insertMenu(@RequestBody  Menu menu){
        return menuService.insertSelective(menu);
    }


    @RequestMapping(value = "/updateMenuById",method = RequestMethod.POST)
    public int updateMenuById(@RequestBody Menu menu){
        return menuService.updateByPrimaryKeySelective(menu);
    }

    @RequestMapping(value = "/queryMenuByParentIdCount",method = RequestMethod.GET)
    public int queryMenuByParentIdCount(@RequestParam("parentId") String parentId){
        Example example= new Example(Menu.class);
        example.createCriteria().andEqualTo("parentId",parentId);
        return menuService.selectCountByExample(example);
    }

    @RequestMapping(value = "/deleteMenuById",method = RequestMethod.GET)
    public int deleteMenuById(@RequestParam("id") String id){
        return menuService.deleteByPrimaryKey(id);
    }


    @RequestMapping(value = "/queryMenuGroupByGroupIdAndGroupType",method = RequestMethod.GET)
    public List<Menu> queryMenuGroupByGroupIdAndGroupType(@RequestParam(value="groupId", required = false) String groupId,
                                                          @RequestParam(value="groupType", required = false) String groupType){

        return menuService.queryMenuGroupByGroupIdAndGroupType(groupId,groupType);
    }



}
