package com.zuer.movieprojecteureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//@EnableEurekaServer //启用Eureka Server
@SpringBootApplication
public class MovieProjectEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieProjectEurekaApplication.class, args);
    }

}
