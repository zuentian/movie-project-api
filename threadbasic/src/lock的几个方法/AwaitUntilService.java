package lock的几个方法;

import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitUntilService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void waitMethod(){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND,10);
            lock.lock();
            System.out.println("wait begin timer="+System.currentTimeMillis());
            condition.awaitUntil(calendar.getTime());//达到一定时间可以自动唤醒自己
            System.out.println("wait end timer="+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void notifyMethod(){
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND,10);
            System.out.println("notify begin timer="+System.currentTimeMillis());
            condition.signalAll();//虽然线程使用awaitUntil,但在等待时间到达前，可以被其他线程提前唤醒
            System.out.println("notify end timer="+System.currentTimeMillis());
        } finally {
            lock.unlock();
        }

    }
}
