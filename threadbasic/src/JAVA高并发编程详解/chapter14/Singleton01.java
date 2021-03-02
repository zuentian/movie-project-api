package JAVA高并发编程详解.chapter14;

/**
 * 懒汉模式
 */
//final 不允许被继承
public final class Singleton01 {

    private static Singleton01 instance = null;

    private Singleton01(){

    }

    public static Singleton01 getInstance(){
        if(null == instance){
            instance = new Singleton01();
        }
        return instance;
    }

}
