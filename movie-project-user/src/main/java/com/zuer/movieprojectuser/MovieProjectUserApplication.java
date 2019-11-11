package com.zuer.movieprojectuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@RestController
@SpringBootApplication
@MapperScan(basePackages = {"com.zuer.movieprojectuser"})
@EnableFeignClients
public class MovieProjectUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieProjectUserApplication.class, args);
    }

}
