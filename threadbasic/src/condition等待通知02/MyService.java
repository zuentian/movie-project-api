package condition等待通知02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Object类中的wait()方法相当于Condition类中的await()方法
 * Object类中的wait(long timeout)方法相当于Condition类中的await(long time,TimeUnit unit)方法
 * Object类中的notify()方法相当于Condition类中的signal()方法
 * Object类中notifyAll()方法相当于Condition类中的signalAll()方法
 */
public class MyService {

    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public void await(){
        try {
            lock.lock();
            System.out.println("  await时间 start为"+System.currentTimeMillis());

            condition.await();
            System.out.println("  await时间 end为"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void signal(){
        try {
            lock.lock();
            System.out.println("  signal时间为"+System.currentTimeMillis());
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}
