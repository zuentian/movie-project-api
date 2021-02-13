package JAVA高并发编程详解.chapter03;

/**
 * sleep会导致当前线程暂停指定的时间，没有CPU时间片的消耗
 * yield只是对CPU调度器的一个提示，如果CPU调度器没有忽略这个提示，它会导致线程上下文切换
 * sleep会使线程短暂block，会在给定时间内释放CPU资源
 * yield会使RUNNING状态的Thread进入RUNNABLE状态
 * sleep几乎百分之百地完成了给定时间的休眠，而yield的提示并不能担保
 * 一个线程sleep另一个线程调用interrupt会捕获到中断信号，而yield则不会
 */
public class ThreadSleep {

    public static void main(String[] args) {
        new Thread(() -> {

                long startTime = System.currentTimeMillis();
                sleep(7_000L);
                long endTime = System.currentTimeMillis();
                System.out.println(String.format("Total spend %d ms",(endTime-startTime)));

        }).start();

        long startTime = System.currentTimeMillis();
        sleep(3_000L);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Main thread total spend %d ms",(endTime-startTime)));
    }

    private static void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }
}
