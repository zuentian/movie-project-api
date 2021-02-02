package 多线程与单例模式.singleton_9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 枚举enum和静态代码块的特性相似，在使用枚举类时。构造方法会被自动调用
 * 也可以应用到其这个特性实现单例设计模式
 */
public enum MyObject {
    connectionFactory;

    private Connection connection;

    private MyObject(){
        try {
            System.out.println("调用了MyObject的构造");

            String url = "jdbc:oracle:thin:@(DESCRIPTION =    (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))    (CONNECT_DATA =      (SERVER = DEDICATED)      (SERVICE_NAME = orcl)    ))";
            String username ="zuer02";
            String password = "password";

            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public Connection getConnection(){
        return connection;
    }
}
