package com.zuer.zuerlvdoubanmovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
/**
 * 指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类
 * 包名如果 com.zuer.zuerlvdoubanmovie 会导致接口上面使用@Service注解无效
 */
@MapperScan(basePackages = {"com.zuer.zuerlvdoubanmovie.dao"})
@EnableEurekaClient
@EnableFeignClients
public class ZuerLvdoubanMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuerLvdoubanMovieApplication.class, args);
    }

}
