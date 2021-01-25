package lock的几个方法;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Service {
    public ReentrantLock lock = new ReentrantLock();
    /**
     * getHoldCount()的作用是查询当前线程保持此锁定的个数
     * 即调用lock()方法的次数
     */
    public void serviceMethod1(){
        try {
            lock.lock();
            System.out.println("serviceMethod1 getHoldCount="+lock.getHoldCount());
            serviceMethod2();
        }finally {
            lock.unlock();
        }

    }

    public void serviceMethod2(){
        try {
            lock.lock();
            System.out.println("serviceMethod2 getHoldCount="+lock.getHoldCount());
        }finally {
            lock.unlock();
        }

    }

    /**
     * getQueueLength()的作用是返回正等待获取此锁定的线程估计数，
     * 比如有5个线程，1个线程首先执行await()方法，那么在调用getQueueLength()方法后
     * 返回值是4，说明有4个线程同时在等待lock的释放
     */
    public void servierMethod3(){
        try {
            lock.lock();
            System.out.println("ThreadName="+Thread.currentThread().getName()+"进入方法");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * getWaitQueueLength(Condition condition)的作用是
     * 返回等待与此锁定相关的给定条件Condition的线程估计数
     * 如果有5个线程，每个线程都执行了同一个condition对象的
     * await()方法，则调用getWaitQueueLength(Condition condition)方法时返回的int值是5
     */
    private Condition newCondition = lock.newCondition();
    public void waitMethod(){
        try {
            lock.lock();
            newCondition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void notityMethod(){
        try {
            lock.lock();
            System.out.println("有"+lock.getWaitQueueLength(newCondition)+"个线程正在等待" +
                    "newCondition start");
            newCondition.signal();
            System.out.println("有"+lock.getWaitQueueLength(newCondition)+"个线程正在等待" +
                    "newCondition end");
        } finally {
            lock.unlock();
        }
    }
}
