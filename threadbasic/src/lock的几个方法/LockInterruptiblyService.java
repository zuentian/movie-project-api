package lock的几个方法;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyService {

    public ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void waitMethod() throws InterruptedException {
        lock.lockInterruptibly();
        try {

            // lock.lock();
            // lockInterruptibly() 的作用是如果当前线程未被中断，则获取锁定如果已经中断则出现异常

            System.out.println("lock begin " + Thread.currentThread().getName());
            for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {
                String newString = new String();
                Math.random();
            }
            System.out.println("lock end " + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
}
