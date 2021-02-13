package JAVA高并发编程详解.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * 捕获中断信号关闭线程
 * 线程的interrupt标识很有可能被擦除，或者逻辑单元中不会调用任何可中断的方法
 * 这个时候就可能关闭不了
 */
public class InterruptThreadExit {
    public static void main(String[] args) throws InterruptedException {


        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println("I will start work t1");
                while (!isInterrupted()){

                }
                System.out.println("I will b exiting t1");
            }
        };

        t1.start();
        TimeUnit.SECONDS.sleep(4);
        System.out.println("System will be shutdown. t1");
        t1.interrupt();

        Thread t2 = new Thread(){
            @Override
            public void run() {
                System.out.println("I will start work t2");
                for(;;){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                System.out.println("I will b exiting t2");
            }
        };

        t2.start();
        TimeUnit.SECONDS.sleep(4);
        System.out.println("System will be shutdown. t2");
        t2.interrupt();
    }

}
