package com.zuer.movielvdoubanservice;

import com.zuer.movielvdoubanservice.controller.AuthController;
import com.zuer.movielvdoubanservice.jwtConfig.JwtAuthenticationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.zuer.movielvdoubanservice")
public class MovieLvdoubanServiceApplicationTests {

    @Autowired
    AuthController authController;
    @Test
    public void contextLoads() throws Exception {

        JwtAuthenticationRequest jw=new JwtAuthenticationRequest("zuentian","000000");

        authController.createAuthenticationToken(jw);
    }

}
