package com.zuer.zuerlvdoubanauth.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@EnableAutoConfiguration
@RequestMapping(value = "/ElementController")
@RestController
public class ElementController {
    @RequestMapping(value = "/queryElementParam",method = RequestMethod.POST)
    public Map<String,Object> queryElementParam(@RequestParam(defaultValue = "10") int limit,
                                                @RequestParam(defaultValue = "1") int page,
                                                String name,
                                                String menuId){
        System.out.println("---------------------------"+limit+","+page+","+menuId+","+name);
        return null;
    }

}
