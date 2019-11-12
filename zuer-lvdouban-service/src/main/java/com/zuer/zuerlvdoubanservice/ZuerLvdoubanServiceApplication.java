package com.zuer.zuerlvdoubanservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = {"com.zuer.zuerlvdoubanservice"})
@EnableFeignClients
@EnableTransactionManagement
public class ZuerLvdoubanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuerLvdoubanServiceApplication.class, args);
    }

}
