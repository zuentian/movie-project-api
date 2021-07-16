package countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);
        for (int i = 0; i < 3; i++) {
            new Thread(new Worker(countDownLatch,i)).start();
        }
        countDownLatch.await();
        System.out.println("3个线程全部执行完成");

    }

    static class Worker implements Runnable{

        private final CountDownLatch countDownLatch;
        private final Integer id;

        Worker(CountDownLatch countDownLatch, Integer id) {
            this.countDownLatch = countDownLatch;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
                doWork();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("第"+id+"个线程完成工作");
        }
        void doWork(){
            System.out.println("第"+id+"个线程开始工作");
        }
    }
}


