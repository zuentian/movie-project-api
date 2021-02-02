package 多线程与单例模式.singleton_10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 违反了"职责单一原则"
 */
public class MyObject {
    public enum MyEnumSingleton{
        connectionFactory;
        private Connection connection;
        private MyEnumSingleton(){
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
    public static Connection getConnection(){
        return MyEnumSingleton.connectionFactory.getConnection();
    }
}
