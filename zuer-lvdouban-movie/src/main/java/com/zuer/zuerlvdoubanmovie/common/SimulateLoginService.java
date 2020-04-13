package com.zuer.zuerlvdoubanmovie.common;

import org.jsoup.Connection;

import java.util.Map;

public interface SimulateLoginService {

    Connection.Response login(String account, String password, String urlCode) throws Exception;

    void requestByGet(Map<String, String> cookies,Map<String,String> data,String urlCode) throws Exception;
}
