package JAVA高并发编程详解.chapter14;

/**
 * Double-Check
 */
public final class SingletonDoubleCheck {

    private volatile static SingletonDoubleCheck instance = null;

    private SingletonDoubleCheck(){

    }
    public static SingletonDoubleCheck getInstance(){
        if(null == instance){
            synchronized (SingletonDoubleCheck.class){
                if(null == instance){
                    instance = new SingletonDoubleCheck();
                }
            }
        }
        return instance;
    }
}
