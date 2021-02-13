package JAVA高并发编程详解.chapter01.策略模式在Thread中的应用;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordQuery {

    private final Connection connection;


    public RecordQuery(Connection connection) {
        this.connection = connection;
    }
    public <T> T query(RowHanler<T> handler,String sql,Object... params) throws SQLException {

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            int index = 1;
            for(Object param : params){
                statement.setObject(index++,param);
            }
            ResultSet resultSet= statement.executeQuery();
            return handler.handle(resultSet);
        }

    }
}
