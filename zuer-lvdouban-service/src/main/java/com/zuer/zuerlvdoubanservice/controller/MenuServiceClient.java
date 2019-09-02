package com.zuer.zuerlvdoubanservice.controller;

import com.zuer.zuerlvdoubancommon.entity.Menu;
import com.zuer.zuerlvdoubanservice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
