package com.zuer.zuerlvdoubanservice.demo;

import com.zuer.zuerlvdoubancommon.demo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/Demo")
public class DemoServiceClient {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public List<Demo> query(){
        List<Demo> demoList=demoService.selectAll();
        System.out.println("测试demoList："+demoList);
        return demoList;
    }
}
