package JAVA高并发编程详解.chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadJoin01 {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 start");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 end");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 start");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 end");
            }
        });

        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(20);
        t1.join();
        t2.join();
        System.out.println("main end");
    }
}
