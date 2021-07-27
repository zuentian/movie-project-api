package com.zuer.zuerlvdoubanauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/*
包含
用户登录，注册，添加，权限管理
菜单、功能、角色的添加，和几者之间的权限分配管理
数据字典的管理
 */

//@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@MapperScan(basePackages = {"com.zuer.zuerlvdoubanauth"})
@EnableEurekaClient
@EnableFeignClients
public class ZuerLvdoubanAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuerLvdoubanAuthApplication.class, args);
    }

}
