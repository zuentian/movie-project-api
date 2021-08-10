package com.zuer.zuerlvdoubanmovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


//@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@MapperScan(basePackages = {"com.zuer.zuerlvdoubanmovie"})
@EnableEurekaClient
@EnableFeignClients
public class ZuerLvdoubanMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuerLvdoubanMovieApplication.class, args);
    }

}
