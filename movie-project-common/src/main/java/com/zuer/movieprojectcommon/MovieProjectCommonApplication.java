package com.zuer.movieprojectcommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class MovieProjectCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieProjectCommonApplication.class, args);
    }

}
