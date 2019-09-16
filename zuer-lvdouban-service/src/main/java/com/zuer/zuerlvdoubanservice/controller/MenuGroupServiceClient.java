package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.MenuGroup;
import com.zuer.zuerlvdoubanservice.service.MenuGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/MenuGroup")
@Transactional(rollbackFor = Exception.class)
public class MenuGroupServiceClient {
    @Autowired
    private MenuGroupService menuGroupService;
    @RequestMapping(value = "/deleteMenuGroupByGroupIdAndMenuType",method = RequestMethod.GET)
    public void deleteMenuGroupByGroupIdAndMenuType(@RequestParam(value="groupId", required = false) String groupId,
                                                    @RequestParam(value="menuType", required = false) String menuType){

        Example example=new Example(MenuGroup.class);
        if (StringUtils.isNotBlank(groupId)) {
            example.createCriteria().andEqualTo("groupId",  groupId);
        }
        if (StringUtils.isNotBlank(menuType)) {
            example.createCriteria().andEqualTo("menuType",  menuType);
        }
        menuGroupService.deleteByExample(example);


    }

    @RequestMapping(value = "/insertMenuGroup",method = RequestMethod.POST)
    public void insertMenuGroup(@RequestBody MenuGroup menuGroup){
        menuGroupService.insertSelective(menuGroup);
    }
}
