package com.zuer.zuerlvdoubanmovie.feignclient;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/AddFeign")
public class AddFeignClient {

    @Transactional(rollbackFor = Exception.class)
    public void addFeignClient(){

        try {
            System.out.println("电影的观影人数的表update+1");
            System.out.println("豆瓣本月的观影人数表update+1");
            System.out.println(1/0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
