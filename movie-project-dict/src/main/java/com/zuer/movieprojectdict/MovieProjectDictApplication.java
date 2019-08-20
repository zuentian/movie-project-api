package com.zuer.movieprojectdict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;


@EnableEurekaClient
@RestController
@SpringBootApplication
public class MovieProjectDictApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieProjectDictApplication.class, args);
    }

}
