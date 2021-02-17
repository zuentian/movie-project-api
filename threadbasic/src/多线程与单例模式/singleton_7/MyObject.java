package 多线程与单例模式.singleton_7;

/**
 * 静态代码块中的代码在使用类的时候就已经执行了，所以可以使用静态代码块的特性来实现单例设计模式
 */
public class MyObject {

    private static class MyObjectHandler{
        private static MyObject myObject = new MyObject();
    }
    private MyObject(){

    }
    public static MyObject getInstance(){
        return MyObjectHandler.myObject;
    }
}
