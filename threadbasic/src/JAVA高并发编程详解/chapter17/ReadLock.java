package JAVA高并发编程详解.chapter17;

class ReadLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;

    ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()){
            //若此时有线程正在进行写操作，或者有写线程在等待并且偏向写锁的标识为true时
            //就会无法获得读锁，只能被挂起
            while (readWriteLock.getWritingWriters() >0 ||
                    (readWriteLock.getPreferWriter() && readWriteLock.getWaitingWriters() > 0)){
                readWriteLock.getMUTEX().wait();
            }
            //成功获得读锁，并且使readingReaders的数量增加
            readWriteLock.incrementReadingReaders();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()){
            //释放锁的过程就是使得当前reading的数量减一
            //将perferWriter设置为true,可以使得writer线程获得更多的机会
            //通知唤醒与Mutex关联的监听器 wait set中线程
            readWriteLock.decrementReadingReaders();
            readWriteLock.changePrefer(true);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
