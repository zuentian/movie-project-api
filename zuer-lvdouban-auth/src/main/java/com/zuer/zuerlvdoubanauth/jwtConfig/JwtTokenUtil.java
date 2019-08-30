package com.zuer.zuerlvdoubanauth.jwtConfig;

import com.zuer.zuerlvdoubanauth.configuration.KeyConfiguration;
import com.zuer.zuerlvdoubancommon.jwt.IJWTInfo;
import com.zuer.zuerlvdoubancommon.jwt.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
    @Value("${jwt.expire}")
    private int expire;
    @Autowired
    private KeyConfiguration keyConfiguration;

    public String generateToken(IJWTInfo jwtInfo) throws Exception {
        System.out.println("生成Token代码 JwtTokenUtil======================");
        return JWTHelper.generateToken(jwtInfo, keyConfiguration.getUserPriKey(), expire);

    }

}
