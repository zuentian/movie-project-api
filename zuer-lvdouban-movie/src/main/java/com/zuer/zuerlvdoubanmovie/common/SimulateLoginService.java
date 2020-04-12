package com.zuer.zuerlvdoubanmovie.common;

import org.jsoup.Connection;

public interface SimulateLoginService {

    Connection.Response login(String account, String password, String urlCode) throws Exception;
}
