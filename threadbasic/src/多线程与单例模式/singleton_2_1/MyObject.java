package 多线程与单例模式.singleton_2_1;

/**
 * 此方法加入同步synchronized关键字得到相同实例对象，但是此种方法运行效率非常低下
 * 是同步运行的，下一个线程想要取得对象，则必须等上一个线程释放锁之后，才可以继续执行
 */
public class MyObject {

    private static MyObject myObject;
    private MyObject(){

    }
    synchronized public static MyObject getInstance(){
        try {
            if(myObject != null){

            }else{
                Thread.sleep(3000);
                myObject = new MyObject();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myObject;
    }
}
