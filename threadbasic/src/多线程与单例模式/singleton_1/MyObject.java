package 多线程与单例模式.singleton_1;

public class MyObject {

    private static MyObject myObject;
    private MyObject(){

    }
    public static MyObject getInstance(){
        //延迟加载
        try {
            if(myObject != null){

            }else {
                //模拟在创建对象之前做一些准备性的工作
                Thread.sleep(3000);
                myObject = new MyObject();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myObject;
    }
}
