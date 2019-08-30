package com.zuer.zuerlvdoubanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = {"com.zuer.zuerlvdoubanservice"})
@EnableFeignClients
public class ZuerLvdoubanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuerLvdoubanServiceApplication.class, args);
    }

}
