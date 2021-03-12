package JAVA高并发编程详解.chapter19;
@FunctionalInterface
public interface Callback<T> {
    //任务完成后会调用该方法，其中T为任务执行后的结果
    void call(T t);
}
