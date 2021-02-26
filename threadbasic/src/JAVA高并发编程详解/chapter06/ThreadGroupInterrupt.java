package JAVA高并发编程详解.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * interrupt一个thread group会导致该group中所有的active线程都被interrupt
 */
public class ThreadGroupInterrupt {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("TestGroup");
        new Thread(group,()->
        {
            while (true){
                try {
                    TimeUnit.MILLISECONDS.sleep(6);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("t1 will exit.");
        },"t1").start();

        new Thread(group,()->
        {
            while (true){
                try {
                    TimeUnit.MILLISECONDS.sleep(6);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("t2 will exit.");
        },"t2").start();

        TimeUnit.MILLISECONDS.sleep(2);
        group.interrupt();
    }
}
