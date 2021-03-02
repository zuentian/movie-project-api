package JAVA高并发编程详解.chapter14;

/**
 * 在Singleton类中并没有instance的静态成员，而是将其放到了静态内部类Holder之中
 * 因此在Singleton类的初始化过程并不会创建Singleton的实例，
 * Holder类中定义了Singleton的静态变量，并且直接进行了实例化，当Holder被主动引用的时候
 * 则会创建Singleton的实例，singleton实例的创建过程在JAVA程序编译时期收集至初始化方法中
 * 该方法又是同步方法，同步方法可以保证内存的可见性、jvm指令的顺序性和原子性。
 */
public final class SingletonHolder {

    private SingletonHolder(){

    }

    //在静态内部类中持有SingletonHolder的实例，并且可被直接初始化
    private static class Holder{
        private static SingletonHolder instance = new SingletonHolder();
    }

    //调用getInstance方法，事实上是获得Holder的instance静态属性
    public static SingletonHolder getInstance(){
        return Holder.instance;
    }

}
