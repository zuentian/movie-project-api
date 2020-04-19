package com.zuer.zuerlvdoubanmovie.common.service;

import org.jsoup.Connection;

import java.util.Map;

public interface SimulateLoginService {

    Connection.Response login(Map<String,String> data, String urlCode) throws Exception;

    Connection.Response requestByGet(Map<String, String> cookies, Map<String,String> data, String urlCode) throws Exception;


    Connection.Response requestByGetFromUrl(Map<String, String> cookies, Map<String,String> data, String url) throws Exception;


}
