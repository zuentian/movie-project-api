package com.zuer.movieprojectuser;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.zuer.movieprojectuser"})
public class MovieProjectUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieProjectUserApplication.class, args);
    }

}
