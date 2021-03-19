package JAVA高并发编程详解.chapter23;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class JavaCountDownLatch {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            final int index = i;
            new Thread(()->{

                try {
                    Thread.sleep(1000 + ThreadLocalRandom.current().nextInt(1000));
                    System.out.println("finish" + index + Thread.currentThread().getName());
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();

        }
        latch.await();
        System.out.println("end");

    }

}
