package com.zuer.zuerlvdoubanauth.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
/*
继承CommandLineRunner之后，springboot在启动完之后会执行这个类，但是必须要加注解@Component
 */
@Component
@EnableAutoConfiguration
public class AuthServerRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
         /*   System.out.println("springboot启动之后执行代码开始+++++++++++++++++");
            Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(keyConfiguration.getUserSecret());
            keyConfiguration.setUserPriKey(keyMap.get("pri"));
            keyConfiguration.setUserPubKey(keyMap.get("pub"));
            keyConfiguration.setServicePriKey(keyMap.get("pri"));
            keyConfiguration.setServicePubKey(keyMap.get("pub"));
            System.out.println("初始化加载用户pubKey");
            try {
                //refreshUserPubKey();
            }catch(Exception e){
                System.err.println("初始化加载用户pubKey失败,1分钟后自动重试!");
            }*/
    }
    /*@Scheduled注解可以实现定时执行*//*
    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshUserPubKey(){
        BaseResponse resp = serviceAuthFeign.getUserPublicKey(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getStatus() == HttpStatus.OK.value()) {
            ObjectRestResponse<byte[]> userResponse = (ObjectRestResponse<byte[]>) resp;
            this.userAuthConfig.setPubKeyByte(userResponse.getData());
        }
    }*/
}
