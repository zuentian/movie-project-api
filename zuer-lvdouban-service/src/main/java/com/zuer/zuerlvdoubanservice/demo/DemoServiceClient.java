package com.zuer.zuerlvdoubanservice.demo;

import com.zuer.zuerlvdoubancommon.demo.Demo;
import io.lettuce.core.ScriptOutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
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



    @RequestMapping(value = "/insertDemo",method = RequestMethod.POST)
    @Transactional
    public void insertDemo(@RequestBody Demo demo){
        demoService.insertSelective(demo);
        System.out.println("+++++++++=+++"+demo);
        int i = 1 / 0;
    }
}
