package JAVA高并发编程详解.chapter17;

class WriteLock implements Lock{
    private final ReadWriteLockImpl readWriteLock;
    WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()){
            //首先使等待获取写入锁的数字加一
            try {
                readWriteLock.incrementWaitingWriters();
                //如果此时有其他线程正在进行读操作，或者写操作，那么当前线程将被挂起
                while (readWriteLock.getReadingReaders() > 0 ||
                        readWriteLock.getWritingWriters() > 0){
                    readWriteLock.getMUTEX().wait();
                }
            } finally {
                //成功获取到了写入锁，使得等待获取写入锁的计数器减一
                this.readWriteLock.decrementWaitingWriters();
            }
            //将正在写入的线程数量加一
            readWriteLock.incrementWritingWriters();

        }

    }

    @Override
    public void unlock() {

    }
}
