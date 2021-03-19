package JAVA高并发编程详解.chapter23;

import java.util.concurrent.TimeUnit;

public abstract class Latch {


    //用于控制多少个线程完成任务时才能打开阀门
    protected int limit;

    //通过构造函数传入limit
    public Latch(int limit){
        this.limit = limit;
    }

    //该方法会使当前线程一直等待，直到所有的线程都完成工作，被阻塞的线程是允许被中断的
    public abstract void await() throws InterruptedException;

    //当任务线程完成工作之后调用该方法使得计数器减一
    public abstract void countDown();

    //获取当前还有多少个线程没有完成任务
    public abstract int getUnarrived();


    public abstract void await(TimeUnit unit,long time) throws InterruptedException,WaitTimeoutException;
}
