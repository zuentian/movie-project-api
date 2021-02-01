package 多线程与单例模式.singleton_2_2;

/**
 * 此方法加入同步synchronized语句块得到相同实例的对象，但是此种方法的运行效率也是非常低的
 * 和synchronized同步方法一样是同步运行的
 */
public class MyObject {
    private static MyObject myObject;

    private MyObject(){

    }

    public static MyObject getInstance(){
        synchronized (MyObject.class){
            try {
                if(myObject != null){

                }else{
                    Thread.sleep(3000);
                    myObject = new MyObject();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return myObject;
    }

}
