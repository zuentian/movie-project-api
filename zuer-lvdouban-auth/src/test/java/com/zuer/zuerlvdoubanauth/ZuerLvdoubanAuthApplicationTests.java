package com.zuer.zuerlvdoubanauth;

import com.zuer.zuerlvdoubanauth.controller.LoginController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZuerLvdoubanAuthApplicationTests {
@Autowired
    LoginController loginController;
    @Test
    public void contextLoads() throws Exception {

    }

}
