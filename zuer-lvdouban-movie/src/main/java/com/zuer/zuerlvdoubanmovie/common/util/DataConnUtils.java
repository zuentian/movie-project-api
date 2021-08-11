package com.zuer.zuerlvdoubanmovie.common.util;

public class DataConnUtils {/*

    private static Connection conn = null;
    *//**
     * 连接数据库的基本信息
     *
     * @return Connection
     * @Title: dbConnection
     *//*
    public static Connection dbConnectionZuer02() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@(DESCRIPTION =    (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))    (CONNECT_DATA =      (SERVER = DEDICATED)      (SERVICE_NAME = orcl)    ))";
            String user = "zuer03";
            String password = "password";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Zuer02 Connection 开启！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static Connection dbConnectionZuer03() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@(DESCRIPTION =    (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))    (CONNECT_DATA =      (SERVER = DEDICATED)      (SERVICE_NAME = orcl)    ))";
            String user = "zuer03";
            String password = "password";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Zuer03 Connection 开启！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    *//**
     * 关闭数据库的连接
     *
     * @return void
     * @Title: dbDisConnection
     *//*
    public static void dbDisConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection 关闭！");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/
}
