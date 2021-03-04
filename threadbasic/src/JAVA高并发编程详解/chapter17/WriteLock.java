package JAVA高并发编程详解.chapter17;

class WriteLock implements Lock{
    private final ReadWriteLockImpl readWriteLock;
    WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {

    }

    @Override
    public void unlock() {

    }
}
