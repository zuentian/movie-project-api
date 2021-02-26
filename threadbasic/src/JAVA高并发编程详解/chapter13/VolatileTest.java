package JAVA高并发编程详解.chapter13;

import JAVA高并发编程详解.chapter23.CountDownLatch;
import JAVA高并发编程详解.chapter23.Latch;

/**
 * volatile关键字不保证原子性
 */
public class VolatileTest {

    private static volatile int i =0 ;

    private static final Latch latch = new CountDownLatch(10);

    private static void inc(){
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int x = 0; x < 1000; x++) {
                    inc();
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(i);
    }
}
