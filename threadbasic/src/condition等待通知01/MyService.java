package condition等待通知01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await(){
        try {
            lock.lock();
            System.out.println("A");
            //调用await之前需要调用lock.lock()代码获得同步监视器
            condition.await();//使当前执行任务的线程进入了等待WAITING状态
            System.out.println("B");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("锁被释放了！");
        }
    }
}
