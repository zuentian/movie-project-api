package lock的几个方法;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitUninterruptiblyService {
    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void testMethod(){
        lock.lock();
        try {
            System.out.println("wait begin");
            condition.await();//如果线程interrupt会报异常
            System.out.println("wait end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
