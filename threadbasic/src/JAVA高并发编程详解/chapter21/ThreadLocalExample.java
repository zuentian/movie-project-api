package JAVA高并发编程详解.chapter21;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

public class ThreadLocalExample {

    public static void main(String[] args) {

        //创建ThreadLocal实例
        ThreadLocal<Integer> tlocal = new ThreadLocal<>();
        //创建十个线程，使用tlocal
        IntStream.range(0,10).forEach(i ->
            new Thread(()->{
                try {
                    //每个线程都会设置tlocal，但是彼此之间的数据是独立的
                    tlocal.set(i);
                    System.out.println(currentThread() + " set i"+tlocal.get());
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(currentThread() + " get i"+tlocal.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start()
        );

    }
}
