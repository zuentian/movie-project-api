package JAVA高并发编程详解.chapter01.策略模式在Thread中的应用;

import java.sql.ResultSet;

public interface RowHanler<T> {
    T handle(ResultSet rs);
}
