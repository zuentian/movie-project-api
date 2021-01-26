package lock的几个方法;

import java.util.concurrent.locks.ReentrantLock;

public class Service03 {

    private ReentrantLock lock;
    public Service03 (boolean isFair){
        super();
        lock = new ReentrantLock(isFair);
    }

    /**
     * isLocked()作用是查询此锁定是否由任意线程保持
     */
    public void serviceMethod(){
        try {
            System.out.println(lock.isLocked());
            lock.lock();
            System.out.println(lock.isLocked());
        } finally {
            lock.unlock();
        }
    }
}
