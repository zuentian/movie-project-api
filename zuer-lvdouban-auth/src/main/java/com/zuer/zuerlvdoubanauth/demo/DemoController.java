package com.zuer.zuerlvdoubanauth.demo;

import com.zuer.zuerlvdoubancommon.demo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableAutoConfiguration
@RequestMapping(value = "/DemoController")
@RestController
public class DemoController {

    @Autowired
    DemoFeignService demoFeignService;
    public List<Demo> query(){
        System.out.println("--------------进入controller层-------------");
        return demoFeignService.query();
    }

}
