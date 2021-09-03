package com.zuer.zuerlvdoubancommon.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Zuer
 * @version 1.0
 * @date 2021/8/30 14:57
 */
public class HttpClientUtils {

    /**
     * @param url_
     * @param param json报文
     * @return
     */
    public static String doPost(String url_, String param) {
        try {
            URL url = new URL(url_);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestProperty("connection","keep-alive");
            connection.connect();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(param);
            writer.close();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return is2String(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String is2String(InputStream inputStream) {
        StringBuilder str = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                str.append(line);
            }
        } catch (IOException e) {
        }
        return str.toString();
    }
}
