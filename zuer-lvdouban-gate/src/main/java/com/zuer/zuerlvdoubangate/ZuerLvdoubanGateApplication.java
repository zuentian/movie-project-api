package com.zuer.zuerlvdoubangate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;


//GlobalGilter 全局过滤器接口与 GatewayFilter 网关过滤器接口具有相同的方法定义。
//全局过滤器是一系列特殊的过滤器，会根据条件应用到所有路由中。网关过滤器是更细粒度的过滤器，作用于指定的路由中。
//具体使用是用户的请求经过gateway，然后由gateway决定去往哪个服务
@RestController
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

@EnableDiscoveryClient
@EnableFeignClients
public class ZuerLvdoubanGateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuerLvdoubanGateApplication.class, args);
    }

}
