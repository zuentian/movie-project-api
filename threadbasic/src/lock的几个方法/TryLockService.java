package lock的几个方法;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockService {
    public ReentrantLock lock = new ReentrantLock();

    public void waitMethod(){
        /*
        tryLock()的作用是仅在调用时锁定未被另一个线程保持的情况下，才获得该锁定
         */
        if(lock.tryLock()){
            System.out.println(Thread.currentThread().getName()+"获得锁");
        }else{
            System.out.println(Thread.currentThread().getName()+"没有获得锁");
        }
    }
    public void waitMethodTimeUnit(){
        try {
        /*
        tryLock()的作用是如果锁定在给定等待时间内没有被另一个线程保持，且当前线程未被中断
        则获取该锁定
         */
            if(lock.tryLock(3, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName()+"获得锁");
                Thread.sleep(1000);
            }else{
                System.out.println(Thread.currentThread().getName()+"没有获得锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }
}
