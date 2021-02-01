package 多线程与单例模式.singleton_5;

/**
 * 使用DCL双检查锁机制 Double-Check Locking
 *
 */
public class MyObject {
    private volatile static MyObject myObject;

    private MyObject(){

    }
    public static MyObject getInstance(){
        try {
            if(myObject != null){

            }else{
                Thread.sleep(3000);
                synchronized (MyObject.class){
                    if(myObject == null){
                        myObject = new MyObject();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myObject;
    }
}
