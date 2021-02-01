package 多线程与单例模式.singleton_7;

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
