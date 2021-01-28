package 多线程与单例模式.singleton_0;

public class MyObject {
    //立即加载方式==饿汉模式
    private static MyObject myObject= new MyObject();
    private MyObject(){

    }
    public static MyObject getInstance(){

        //此代码版本为立即加载
        //此版本代码的缺点是不能有其他实例变量
        //getInstance()方法没有同步，有可能出现非线程安全问题
        return myObject;
    }
}
