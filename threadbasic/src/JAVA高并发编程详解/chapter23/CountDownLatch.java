package JAVA高并发编程详解.chapter23;

import java.util.concurrent.TimeUnit;

public class CountDownLatch extends Latch {


    public CountDownLatch(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {

        synchronized (this){
            //当limit>0时，当前线程进入阻塞状态
            while (limit > 0){
                System.out.println("limit>0 进入阻塞！");
                this.wait();
            }
            System.out.println("阻塞结束");
        }
    }

    @Override
    public void countDown() {
        synchronized (this){
            if(limit <= 0){
                throw new IllegalStateException("all of task already arrived");
            }
            //使limit减一，并且通知阻塞线程
            limit --;
            System.out.println("唤醒线程");
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {
        //返回有多少线程还未完成任务
        return limit;
    }

    @Override
    public void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutException {

        if(time <= 0){
            throw new IllegalArgumentException("The time is invalid");
        }
        long remainingNanos = unit.toNanos(time);//将time转换成为纳秒

        final long endNanos = System.nanoTime()+remainingNanos;

        synchronized (this){
            while (limit > 0){
                //如果超时则抛出WaitTimeoutException
                if(TimeUnit.NANOSECONDS.toMillis(remainingNanos) <= 0){
                    throw new WaitTimeoutException("The wait time over specify time.");
                }
                //等待remainingNanos，在等待的过程中有可能会被中断，需要重新计算remainingNanos
                this.wait(TimeUnit.NANOSECONDS.toMillis(remainingNanos));
                remainingNanos = endNanos -System.nanoTime();

            }
        }

    }
}
