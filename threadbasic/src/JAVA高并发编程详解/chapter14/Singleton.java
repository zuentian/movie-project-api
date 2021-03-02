package JAVA高并发编程详解.chapter14;

/**
 * 饿汉式
 */
//final 不允许被继承
public final class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton(){

    }

    public static Singleton getInstance(){
        return instance;
    }

}
