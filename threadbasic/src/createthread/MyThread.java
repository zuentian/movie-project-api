package createthread;

/**
 * 1)定义类继承Thread
 */
public class MyThread  extends Thread{
    //2)重写Thread父类中run()

    @Override
    public void run() {
        System.out.println("这是子线程");
    }
}
