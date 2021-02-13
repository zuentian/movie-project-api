package JAVA高并发编程详解.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * 使用volatile开关控制结束线程
 */
public class FlagThreadExit {
    static class MyTask extends Thread{
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("I will start work");
            while (!closed && !isInterrupted()){

            }
            System.out.println("I will be exiting.");
        }

        public void close(){
            this.closed = true;
            this.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask t = new MyTask();
        t.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println("System will be shutdown.");
        t.close();
    }
}
