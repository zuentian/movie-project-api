package JAVA高并发编程详解.chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

public class BooleanLock implements Lock {

    private Thread currentThread;//当前拥有锁的线程

    private boolean locked = false;//当前该锁有没有被任何线程获得或者已经释放

    private  final List<Thread> blockedList = new ArrayList<>();//存储哪些线程在获取当前线程时进入了阻塞状态



    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while (locked){
                //暂存当前线程
                final Thread tempThread = currentThread();
                try {
                    if(!blockedList.contains(tempThread)){
                        blockedList.add(tempThread);
                    }
                    this.wait();
                } catch (InterruptedException e) {
                    //如果当前线程在wait时被中断，则从blockedList中将其删除
                    //避免内存泄露
                    blockedList.remove(tempThread);
                    //继续抛出中断异常
                    throw e;
                }
            }
            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if(mills <= 0){
                this.lock();
            }else {
                long remainingMills = mills;
                long endMills = currentTimeMillis() + remainingMills;
                while (locked){
                    if(remainingMills <= 0){
                        throw new TimeoutException(
                                "can not get the lock during "+ mills);
                    }
                    if(!blockedList.contains(currentThread)){
                        blockedList.add(currentThread);
                    }
                    this.wait(remainingMills);
                    remainingMills = endMills - currentTimeMillis();
                }
                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            if(currentThread == currentThread()){
                this.locked = false;
                Optional.of(currentThread().getName()+" release the lock.")
                        .ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
