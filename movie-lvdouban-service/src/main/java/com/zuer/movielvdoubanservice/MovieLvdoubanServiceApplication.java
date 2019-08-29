package com.zuer.movielvdoubanservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.zuer.movielvdoubanservice.mapper")
public class MovieLvdoubanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieLvdoubanServiceApplication.class, args);
    }

}
