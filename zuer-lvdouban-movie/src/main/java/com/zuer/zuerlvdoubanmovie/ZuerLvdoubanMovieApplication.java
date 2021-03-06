package com.zuer.zuerlvdoubanmovie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@MapperScan(basePackages = {"com.zuer.zuerlvdoubanmovie"})
@EnableEurekaClient
@EnableFeignClients
public class ZuerLvdoubanMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuerLvdoubanMovieApplication.class, args);
    }

}
