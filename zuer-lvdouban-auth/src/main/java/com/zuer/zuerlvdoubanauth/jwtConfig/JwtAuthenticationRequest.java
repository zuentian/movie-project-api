package com.zuer.zuerlvdoubanauth.jwtConfig;

import lombok.Data;

@Data
public class JwtAuthenticationRequest {
    private static final long serialVersionUID = -8445943548965154778L;

    private String username;
    private String password;


    public JwtAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public JwtAuthenticationRequest() {
    }
}
