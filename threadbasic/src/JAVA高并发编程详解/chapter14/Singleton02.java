package JAVA高并发编程详解.chapter14;

/**
 *懒汉式+数据同步的方式既满足了懒加载又能够百分之百地保证instance实例的唯一性
 *但是synchronized关键字天生的排他性导致了getInstance方法只能在同一时刻被一个线程所访问，性能低下
 */
//final 不允许被继承
public final class Singleton02 {

    private static Singleton02 instance = null;

    private Singleton02(){

    }

    public static synchronized Singleton02 getInstance(){
        if(null == instance){
            instance = new Singleton02();
        }
        return instance;
    }
}
