package JAVA高并发编程详解.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * 一个线程内部存在着名为interrup flag的标识，如果一个线程被interrupt，
 * 那么他的flag将会被设置，
 * 但是如果当前线程正在执行可中断方法被阻塞时，调用interrupt方法将其中断，
 * 反而导致flag被清除
 * 如果一个线程已经是死亡状态，那么尝试对其的interrupt会直接被忽略
 */
public class ThreadInterrupt  {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Oh,I am be interrupted.");
            }
        });
        thread.start();
        TimeUnit.MICROSECONDS.sleep(2);
        thread.interrupt();
    }

}
