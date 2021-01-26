package lock的几个方法;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service01 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod(){
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notityMethod(){
        try {
            lock.lock();
            /*
            hasWaiters(Condition condition)的作用是查询是否有线程正在等待与此锁定有关的condition条件
             */
            System.out.println("有没有线程正在等待newCondition?"+lock.hasWaiters(condition)
            +" 线程数是多少？"+lock.getWaitQueueLength(condition));
            condition.signal();
            System.out.println("有没有线程正在等待newCondition?"+lock.hasWaiters(condition)
                    +" 线程数是多少？"+lock.getWaitQueueLength(condition));
        } finally {
            lock.unlock();
        }
    }
}
