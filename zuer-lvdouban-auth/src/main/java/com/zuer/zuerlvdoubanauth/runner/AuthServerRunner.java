package com.zuer.zuerlvdoubanauth.runner;

import com.zuer.zuerlvdoubanauth.configuration.KeyConfiguration;
import com.zuer.zuerlvdoubancommon.jwt.RsaKeyHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.Map;
/*
继承CommandLineRunner之后，springboot在启动完之后会执行这个类，但是必须要加注解@Component
 */
@Component
@EnableAutoConfiguration
public class AuthServerRunner implements CommandLineRunner {

    @Autowired
    private KeyConfiguration keyConfiguration;

    @Override
    public void run(String... args) throws Exception {
            System.out.println("springboot启动之后执行代码开始+++++++++++++++++");
            Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(keyConfiguration.getUserSecret());
            keyConfiguration.setUserPriKey(keyMap.get("pri"));
            keyConfiguration.setUserPubKey(keyMap.get("pub"));
            keyConfiguration.setServicePriKey(keyMap.get("pri"));
            keyConfiguration.setServicePubKey(keyMap.get("pub"));
    }

}
