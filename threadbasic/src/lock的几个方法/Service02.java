package lock的几个方法;

import java.util.concurrent.locks.ReentrantLock;

public class Service02 {
    private ReentrantLock lock;

    public Service02(Boolean isFair){
        super();
        if(null == isFair){
            lock = new ReentrantLock();
        }else {
            lock = new ReentrantLock(isFair);
        }
    }
    public void serviceMethod(){
        try {
            lock.lock();
            System.out.println("公平锁情况："+lock.isFair());
        }finally {
            lock.unlock();
        }

    }
}
