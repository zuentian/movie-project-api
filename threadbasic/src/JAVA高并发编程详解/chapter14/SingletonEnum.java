package JAVA高并发编程详解.chapter14;

/**
 * 枚举方法
 */
//枚举类型本身是final的，不允许被继承
public enum SingletonEnum {


    INSTANCE;

    SingletonEnum(){
        System.out.println("INSTANCE will be initialized immediately");
    }

    public static void method(){

    }
    public static SingletonEnum getInstance(){
        return INSTANCE;
    }


}
